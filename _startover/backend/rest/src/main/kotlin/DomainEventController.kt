package de.holisticon.holibank.backend.rest

import com.fasterxml.jackson.databind.ObjectMapper
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class DomainEventController {

  @Bean
  fun eventSerializer(objectMapper: ObjectMapper) = JacksonSerializer.builder()
    .objectMapper(objectMapper)
    .build()

  @Bean
  fun domainEvents(repository: DomainEventRepository) = router {
    "/api/events".nest {
      (accept(APPLICATION_JSON)).nest {
        GET("/") { _ ->
          ServerResponse.ok()
            .body(BodyInserters.fromObject(repository.findAll()))
        }
      }

    }
  }
}
