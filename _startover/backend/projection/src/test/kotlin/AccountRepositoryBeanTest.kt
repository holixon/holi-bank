package de.holisticon.holibank.backend.projection

import de.holisticon.holibank.backend.core.api.AccountId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountRepositoryBeanTest {

  private val repository = AccountRepositoryBean()

  @Test
  internal fun `existsById false`() {
    assertThat(repository.existsById(AccountId("1"))).isFalse()
  }

  @Test
  internal fun `existsById true`() {
    repository.save(Account(AccountId("1"), 100))
    assertThat(repository.existsById(AccountId("1"))).isTrue()
  }

  @Test
  internal fun `stores account`() {
    assertThat(repository.count()).isEqualTo(0)

    repository.save(Account(AccountId("1"), 100))

    assertThat(repository.count()).isEqualTo(1)
  }

  @Test
  internal fun `finds account`() {
    val account = repository.save(Account(AccountId("1"), 100))

    assertThat(repository.findOne(AccountId("1"))).isEqualTo(account)
  }

  @Test
  internal fun `finds all`() {
    assertThat(repository.findAll()).isEmpty()

    repository.save(Account(AccountId("1"), 100))
    repository.save(Account(AccountId("2"), 200))

    assertThat(repository.count()).isEqualTo(2)

    assertThat(repository.findAll()).hasSize(2)
  }
}
