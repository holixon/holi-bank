package de.holisticon.holibank.backend.core.api

import org.axonframework.serialization.Revision

sealed class AccountEvent(open val id: AccountId) {

  @Revision("1")
  data class AccountCreated(
    override val id: AccountId,
    val initialBalance: Int
  ) : AccountEvent(id)

  @Revision("1")
  data class BalanceChanged(
    override val id: AccountId,
    val diff: Int
  ) : AccountEvent(id)
}
