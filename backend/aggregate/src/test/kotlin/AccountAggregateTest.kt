package de.holisticon.holibank.backend.aggregate

import com.tngtech.jgiven.annotation.ProvidedScenarioState
import de.holisticon.holibank.backend.core.api.AccountCommand.*
import de.holisticon.holibank.backend.core.api.AccountEvent.AccountCreated
import de.holisticon.holibank.backend.core.api.AccountEvent.BalanceChanged
import de.holisticon.holibank.backend.core.api.AccountId
import io.toolisticon.addons.axon.jgiven.AggregateFixtureScenarioTest
import io.toolisticon.addons.jgiven.GIVEN
import io.toolisticon.addons.jgiven.THEN
import io.toolisticon.addons.jgiven.WHEN
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.Test

internal class AccountAggregateTest : AggregateFixtureScenarioTest<AccountAggregate>() {

  @ProvidedScenarioState
  private val fixture = AggregateTestFixture(AccountAggregate::class.java)

  private val id = AccountId("1")

  @Test
  internal fun `an account can be created with initial balance=0`() {
    GIVEN
      .noPriorActivity()

    WHEN
      .command(CreateAccount(id))

    THEN
      .expectEvent(AccountCreated(id, 0))
  }


  @Test
  internal fun `money can be deposited`() {
    GIVEN
      .event(AccountCreated(id, 0))

    WHEN
      .command(Deposit(id, 10))

    THEN
      .expectEvent(BalanceChanged(id, 10))
  }



  @Test
  internal fun `money can be withdrawn if sufficient balance`() {
    GIVEN
      .event(AccountCreated(id, 100))

    WHEN
      .command(Withdraw(id, 20))

    THEN
      .expectEvent(BalanceChanged(id, -20))
  }



  @Test
  internal fun `withdrawal fails for insufficient balance`() {
    GIVEN
      .event(AccountCreated(id, 10))

    WHEN
      .command(Withdraw(id, 20))

    THEN
      . expectExceptionMessage("Insufficient balance:10, withdrawal:20.")
  }
}
