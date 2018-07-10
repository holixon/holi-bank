package de.holisticon.bank.domain

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.Test
import java.math.BigDecimal

class AccountTest {

  private val fixture = AggregateTestFixture(Account::class.java)

  @Test
  fun `an account is created with initial balance`() {
    fixture
      .givenNoPriorActivity()
      .`when`(CreateAccount("1"))
      .expectEvents(AccountCreated("1", 0))
  }

  @Test
  fun `money can be deposited`() {
    fixture
      .given(AccountCreated("1", 0))
      .`when`(Deposit("1", 10))
      .expectEvents(BalanceChanged("1", 10))
  }

  @Test
  fun `money can be withdrawn if sufficient balance`() {
    fixture
      .given(AccountCreated("1", 100))
      .`when`(Withdraw("1", 20))
      .expectEvents(BalanceChanged("1", -20))
  }

  @Test
  fun `withdrawal fails for insufficient balance`() {
    fixture
      .given(AccountCreated("1", 10))
      .`when`(Withdraw("1",20))
      .expectException(InsufficientBalance::class.java)
      .expectExceptionMessage("Insufficient balance:10, withdrawal:20.")
  }
}
