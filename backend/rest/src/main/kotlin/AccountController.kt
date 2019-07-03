package de.holisticon.holibank.backend.rest

import de.holisticon.holibank.backend.api.rest.CreateAccountDto
import de.holisticon.holibank.backend.core.api.AccountCommand
import de.holisticon.holibank.backend.core.api.AccountId
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono.fromFuture
import java.net.URI

@Configuration
class AccountController {

  @Bean
  fun accounts(commandGateway: CommandGateway) = router {
    "/api/accounts".nest {
      (accept(APPLICATION_JSON)).nest {
        POST("/") { r ->
          r.bodyToMono(CreateAccountDto::class.java)
            .map { AccountCommand.CreateAccount(AccountId(it.id), it.balance.intValueExact()) }
            .map { cmd -> commandGateway.send<String>(cmd) }
            .flatMap {
              fromFuture(it).flatMap {
                ServerResponse.created(URI.create("/api/accounts/${it}")).build()
              }

            }
        }
      }
    }
  }
//
//  @Bean
//  fun rest(queryGateway: QueryGateway, commandGateway: CommandGateway, repo: DomainEventRepository) = router {
//    "/api/accounts"
//      .nest {
////        /**
////         * POST - create new account
////         */
////        (accept(APPLICATION_JSON)).nest {
////          POST("/") { r ->
////            r.bodyToMono(CreateAccount::class.java)
////              .flatMap { cmd ->
////                commandGateway.send<Command.CreateAccount>(cmd)
////
////                ServerResponse.created(URI.create("/api/accounts/${cmd.id}")).build()
////              }
////          }
////          /**
////           * PUT - deposit money
////           */
////          PUT("/{accountId}") { r ->
////            r.bodyToMono(Command.Deposit::class.java)
////              .doOnNext { cmd -> commandGateway.send<Command.Deposit>(cmd) }
////              .then(ServerResponse.ok().build())
////          }
//          GET("/g/{accountId}") { r ->
//            ok()
//              .body(fromFuture(CurrentBalance.Query(AccountId(r.pathVariable("accountId"))).send(queryGateway)), CurrentBalance.Result::class.java)
//          }
//        }
//        /**
//         * GET - stream changes
//         */
//        (accept(TEXT_EVENT_STREAM)).nest {
//          GET("/{accountId}") { r ->
//            ok().bodyToServerSentEvents(
//              CurrentBalance.Query(AccountId(r.pathVariable("accountId"))).subscribe(queryGateway)
//            )
//          }
//        }
//
//      }
//    "/api/events".nest {
//      (accept(APPLICATION_JSON)).nest {
//        GET("/") { _ ->
//          ServerResponse.ok()
//            .body(BodyInserters.fromObject(repo.findAll()))
//        }
//      }
//    }
//  }

//
}
