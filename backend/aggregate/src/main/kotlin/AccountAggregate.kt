package de.holisticon.holibank.backend.aggregate

import de.holisticon.holibank.backend.core.api.AccountCommand.*
import de.holisticon.holibank.backend.core.api.AccountEvent
import de.holisticon.holibank.backend.core.api.AccountEvent.AccountCreated
import de.holisticon.holibank.backend.core.api.AccountId
import de.holisticon.holibank.backend.core.api.HoliBankException.InsufficientBalance
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply

data class AccountAggregate(
  @AggregateIdentifier
  var id: AccountId? = null,
  var balance: Int = 0
) {

  companion object {
    @CommandHandler
    @JvmStatic
    fun create(cmd: CreateAccount) = apply(
      AccountCreated(cmd.id, cmd.initialBalance)
    ).let { AccountAggregate() }
  }


  @CommandHandler
  fun handle(cmd: Deposit) {
    require(cmd.amount > 0) { "deposit amount must be > 0" }
    apply(AccountEvent.BalanceChanged(cmd.id, cmd.amount))
  }

  @CommandHandler
  fun handle(cmd: Withdraw) {
    require(cmd.amount > 0) { "withdraw amount must be > 0" }

    if (balance >= cmd.amount)
      apply(AccountEvent.BalanceChanged(cmd.id, -cmd.amount))
    else
      throw InsufficientBalance(balance, cmd.amount)
  }

  @EventSourcingHandler
  fun on(event: AccountEvent.BalanceChanged) {
    this.balance += event.diff
  }

  @EventSourcingHandler
  fun on(event: AccountCreated) {
    this.id = event.id
    this.balance = event.initialBalance
  }
}
