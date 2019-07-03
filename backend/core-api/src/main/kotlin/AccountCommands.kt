package de.holisticon.holibank.backend.core.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision

/**
 * Commands for Account.
 */
sealed class AccountCommand(
  @TargetAggregateIdentifier
  open val id: AccountId) {

  /**
   * Creates a new account identified by id. If no initialBalance is given, it is 0.
   */
  @Revision("1")
  data class CreateAccount(
    override val id: AccountId,
    val initialBalance: Int = 0
  ) : AccountCommand(id)

  @Revision("1")
  data class Deposit(
    override val id: AccountId,
    val amount: Int
  ) : AccountCommand(id)

  @Revision("1")
  data class Withdraw(
    override val id: AccountId,
    val amount: Int
  ) : AccountCommand(id)

}
