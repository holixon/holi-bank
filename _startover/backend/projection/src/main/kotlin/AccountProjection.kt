package de.holisticon.holibank.backend.projection

import de.holisticon.holibank.backend.core.api.AccountEvent.AccountCreated
import de.holisticon.holibank.backend.core.api.AccountEvent.BalanceChanged
import de.holisticon.holibank.backend.core.api.AccountQuery.CurrentBalance
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.axonframework.queryhandling.QueryUpdateEmitter

class AccountProjection(
  val repository: AccountRepository,
  private val queryUpdateEmitter: QueryUpdateEmitter
) {

  @EventHandler
  fun on(evt: AccountCreated) {
    require(!repository.existsById(evt.id)) { "account ${evt.id} already exists" }

    saveAndNotify(Account(evt.id, evt.initialBalance))
  }

  @EventHandler
  fun on(evt: BalanceChanged) {
    require(repository.existsById(evt.id)) { "account ${evt.id} does not exist" }

    val existing = repository.findOne(evt.id)!!

    saveAndNotify(existing.changeBalance(evt.diff))
  }


  @QueryHandler
  fun query(query: CurrentBalance.Query): CurrentBalance.Result {
    val account = repository.findOne(query.id)!! // TODO: deal with null

    return CurrentBalance.Result(account.id, account.balance)
  }

  private fun QueryUpdateEmitter.updateCurrentBalance(updated: Account) = emit(
    CurrentBalance.Query::class.java,
    { query -> query.id.equals(updated.id) },
    CurrentBalance.Result(updated.id, updated.balance)
  )

  private fun saveAndNotify(account: Account) : Account = account.apply {
    repository.save(this)
    queryUpdateEmitter.updateCurrentBalance(this)
  }
}
