package de.holisticon.bank

import de.holisticon.bank.domain.CreateAccount
import de.holisticon.bank.domain.CurrentBalance
import de.holisticon.bank.domain.Deposit
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.math.BigDecimal

fun main(args: Array<String>) = runApplication<HoliBankApplication>(*args).let { Unit }

@SpringBootApplication
class HoliBankApplication

@RestController
@RequestMapping("/api")
class Controller(val queryGateway: QueryGateway, val commandGateway: CommandGateway) {

  @GetMapping(value = "/account/{accountId}", produces = arrayOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
  fun streamResult(@PathVariable("accountId") accountId: String): Flux<CurrentBalance.Result> {
    val subscription = queryGateway.subscriptionQuery(CurrentBalance.Query(accountId), CurrentBalance.Result::class.java, CurrentBalance.Result::class.java)

    return subscription.initialResult().concatWith(subscription.updates())
  }

  @PostMapping("/accounts")
  fun create(@RequestBody cmd:CreateAccount) {
    commandGateway.send<CreateAccount>(cmd)
  }

  @PutMapping("/account/{accountId}")
  fun update(@PathVariable("accountId") accountId: String, @RequestBody cmd:Deposit) {
    commandGateway.send<Deposit>(cmd)
  }
}

typealias AccountId = String
