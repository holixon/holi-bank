@file:Suppress("PackageDirectoryMismatch", "unused")

package de.holisticon.bank.domain

import de.holisticon.bank.AccountId
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Account {

  companion object {
    @CommandHandler
    @JvmStatic
    fun create(cmd: CreateAccount): Account = apply(AccountCreated(cmd.id, cmd.initialBalance)).let { Account() }
  }

  @AggregateIdentifier
  private lateinit var id: AccountId
  private var balance: Int = 0

  @CommandHandler
  fun handle(cmd: Deposit) {
    apply(BalanceChanged(cmd.id, cmd.amount))
  }

  @CommandHandler
  fun handle(cmd: Withdraw) {
    if (balance >= cmd.amount)
      apply(BalanceChanged(cmd.id, -cmd.amount))
    else
      throw InsufficientBalance(balance, cmd.amount)
  }

  @EventSourcingHandler
  fun on(event: BalanceChanged) {
    this.balance += event.diff
  }

  @EventSourcingHandler
  fun on(event: AccountCreated) {
    this.id = event.id
    this.balance = event.initialBalance
  }
}
