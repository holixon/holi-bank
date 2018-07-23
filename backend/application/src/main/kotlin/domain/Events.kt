package de.holisticon.bank.domain

import de.holisticon.bank.AccountId
import org.axonframework.serialization.Revision
import java.math.BigDecimal

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


