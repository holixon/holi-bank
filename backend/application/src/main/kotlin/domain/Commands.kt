package de.holisticon.bank.domain

import de.holisticon.bank.AccountId
import org.axonframework.modelling.command.TargetAggregateIdentifier

sealed class Command(@TargetAggregateIdentifier open val id:AccountId)

data class CreateAccount(
  override val id: AccountId,
  val initialBalance: Int = 0
) : Command(id)

data class Deposit(
  override val id: AccountId,
  val amount: Int
) : Command(id)

data class Withdraw(
  override val id: AccountId,
  val amount: Int
) : Command(id)


