package de.holisticon.bank.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision

sealed class Command(@TargetAggregateIdentifier open val id:AccountId)

@Revision("1")
data class CreateAccount(
  override val id: AccountId,
  val initialBalance: Int = 0
) : Command(id)

@Revision("1")
data class Deposit(
  override val id: AccountId,
  val amount: Int
) : Command(id)

@Revision("1")
data class Withdraw(
  override val id: AccountId,
  val amount: Int
) : Command(id)


