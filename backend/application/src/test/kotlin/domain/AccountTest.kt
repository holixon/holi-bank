package de.holisticon.bank.domain

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.Test

class AccountTest {

  private val fixture = AggregateTestFixture(Account::class.java)

  @Test
  fun `an account is created with initial balance`() {
    fixture
      .givenNoPriorActivity()
      .`when`(CreateAccount(AccountId("1")))
      .expectEvents(AccountCreated(AccountId("1"), 0))
  }

  @Test
  fun `money can be deposited`() {
    fixture
      .given(AccountCreated(AccountId("1"), 0))
      .`when`(Deposit(AccountId("1"), 10))
      .expectEvents(BalanceChanged(AccountId("1"), 10))
  }

  @Test
  fun `money can be withdrawn if sufficient balance`() {
    fixture
      .given(AccountCreated(AccountId("1"), 100))
      .`when`(Withdraw(AccountId("1"), 20))
      .expectEvents(BalanceChanged(AccountId("1"), -20))
  }

  @Test
  fun `withdrawal fails for insufficient balance`() {
    fixture
      .given(AccountCreated(AccountId("1"), 10))
      .`when`(Withdraw(AccountId("1"),20))
      .expectException(InsufficientBalance::class.java)
      .expectExceptionMessage("Insufficient balance:10, withdrawal:20.")
  }
}
