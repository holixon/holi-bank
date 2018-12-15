package de.holisticon.bank

import de.holisticon.bank.domain.AccountId
import de.holisticon.bank.domain.CreateAccount
import de.holisticon.bank.domain.CurrentBalance
import de.holisticon.bank.domain.Deposit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters.fromObject
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class HoliBankApplicationTests(@Autowired private val webClient: WebTestClient) {

  private val id = UUID.randomUUID().toString()

  @Test
  fun `create account`() {
    webClient.post().uri("/api/accounts")
      .body(fromObject(CreateAccount(AccountId(id))))
      .exchange()
      .expectStatus().isCreated
      .expectHeader().valueEquals("Location", "/api/accounts/$id")
  }

  @Test
  fun `can deposit money`() {
    webClient.post().uri("/api/accounts")
      .body(fromObject(CreateAccount(AccountId(id))))
      .exchange()

    webClient.put().uri("/api/accounts/$id")
      .body(fromObject(Deposit(AccountId(id), 100)))
      .exchange()


    val result = webClient.get().uri("/api/accounts/g/$id")
      .exchange()
      .expectStatus().isOk
      .expectBody(CurrentBalance.Result::class.java)
      .returnResult()
      .responseBody!!

    Assertions.assertThat(result.balance).isEqualTo(100)

  }



}
