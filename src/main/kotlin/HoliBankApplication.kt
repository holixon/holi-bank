package de.holisticon.bank

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import de.holisticon.bank.domain.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry
import org.axonframework.queryhandling.QueryGateway
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.beans
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.TEXT_EVENT_STREAM
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono.fromFuture
import java.net.URI

fun main(args: Array<String>) = runApplication<HoliBankApplication>(*args){
  addInitializers(
    beans {
      bean {
        KotlinModule()
      }
    }
  )
}.let { Unit }

@SpringBootApplication
class HoliBankApplication {
  @Bean
  fun rest(queryGateway: QueryGateway, commandGateway: CommandGateway, repo: DomainEventRepository) = router {
    "/api/accounts"
      .nest {
        /**
         * POST - create new account
         */
        (accept(APPLICATION_JSON)).nest {
          POST("/") { r ->
            r.bodyToMono(CreateAccount::class.java)
              .flatMap { cmd ->
                commandGateway.send<CreateAccount>(cmd)

                ServerResponse.created(URI.create("/api/accounts/${cmd.id}")).build()
              }
          }
          /**
           * PUT - deposit money
           */
          PUT("/{accountId}") { r ->
            r.bodyToMono(Deposit::class.java)
              .doOnNext { cmd -> commandGateway.send<Deposit>(cmd) }
              .then(ServerResponse.ok().build())
          }
          GET("/g/{accountId}") { r ->
            ok()
              .body(fromFuture(CurrentBalance.Query(r.pathVariable("accountId")).send(queryGateway)), CurrentBalance.Result::class.java)
          }
        }
        /**
         * GET - stream changes
         */
        (accept(TEXT_EVENT_STREAM)).nest {
          GET("/{accountId}") { r ->
            ok().bodyToServerSentEvents(
              CurrentBalance.Query(r.pathVariable("accountId")).subscribe(queryGateway)
            )
          }
        }

      }
    "/api/events".nest {
      (accept(APPLICATION_JSON)).nest {
        GET("/") { _ ->
          ServerResponse.ok()
            .body(BodyInserters.fromObject(repo.findAll()))
        }
      }
    }
  }

  @Bean
  fun eventSerializer(objectMapper: ObjectMapper) = JacksonSerializer(objectMapper)

}

interface DomainEventRepository : PagingAndSortingRepository<DomainEventEntry, Long>

typealias AccountId = String
