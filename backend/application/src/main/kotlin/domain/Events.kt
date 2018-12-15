package de.holisticon.bank.domain

import org.axonframework.serialization.Revision

sealed class Event(open val id: AccountId)

@Revision("1")
data class AccountCreated(
  override val id : AccountId,
  val initialBalance: Int
) : Event(id)

@Revision("1")
data class BalanceChanged(
  override val id : AccountId,
  val diff: Int
) : Event(id)


