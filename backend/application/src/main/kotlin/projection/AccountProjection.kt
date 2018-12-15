@file:Suppress("PackageDirectoryMismatch", "unused")

package de.holisticon.bank.projection

import de.holisticon.bank.domain.*
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.axonframework.queryhandling.QueryUpdateEmitter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AccountProjection(private val queryUpdateEmitter: QueryUpdateEmitter) {

  private val log = LoggerFactory.getLogger(AccountProjection::class.java)

  private val accounts = mutableMapOf<AccountId, Int>()

  @EventHandler
  fun on(event: AccountCreated) {
    accounts[event.id] = event.initialBalance
    updateQuery(event.id)
  }

  @EventHandler
  fun on(event: BalanceChanged) {
    accounts[event.id] = accounts.getOrDefault(event.id, 0) + event.diff

    log.info("accounts: $accounts")
    updateQuery(event.id)
  }

  @QueryHandler
  fun query(query: CurrentBalance.Query): CurrentBalance.Result =
    if (accounts.containsKey(query.id))
      CurrentBalance.Result(query.id, accounts.get(query.id)!!)
    else
      throw AccountNotExistent(query.id)


  private fun updateQuery(accountId: AccountId) {
    queryUpdateEmitter.emit(
      CurrentBalance.Query::class.java,
      { query -> query.id.equals(accountId) },
      CurrentBalance.Result(accountId, accounts[accountId]!!))
  }

}
