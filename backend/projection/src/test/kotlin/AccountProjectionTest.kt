package de.holisticon.holibank.backend.projection

import de.holisticon.holibank.backend.core.api.AccountEvent.AccountCreated
import de.holisticon.holibank.backend.core.api.AccountEvent.BalanceChanged
import de.holisticon.holibank.backend.core.api.AccountId
import de.holisticon.holibank.backend.core.api.AccountQuery.CurrentBalance
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.queryhandling.SimpleQueryUpdateEmitter
import org.junit.jupiter.api.Test

internal class AccountProjectionTest {

  val repository = AccountRepositoryBean()
  val handler = AccountProjection(repository, SimpleQueryUpdateEmitter.builder().build()) // TODO mock?

  @Test
  internal fun `create account on AccountCreated`() {
    assertThat(repository.findAll()).isEmpty()

    handler.on(AccountCreated(AccountId("1"), 100))

    assertThat(repository.findOne(AccountId("1"))).isEqualTo(Account(AccountId("1"),100))
  }

  @Test
  internal fun `update balance on BalanceChanged`() {
    handler.on(AccountCreated(AccountId("1"), 100))

    handler.on(BalanceChanged(AccountId("1"), -20))

    assertThat(repository.findOne(AccountId("1"))!!.balance).isEqualTo(80)
  }

  @Test
  internal fun `find current balance`() {
    handler.on(AccountCreated(AccountId("1"), 100))

    val result = handler.query(CurrentBalance.Query(AccountId("1")))

    assertThat(result).isEqualTo(CurrentBalance.Result(AccountId("1"), 100))
  }
}
