package de.holisticon.holibank.backend.projection

import de.holisticon.holibank.backend.core.api.AccountId
import java.util.concurrent.ConcurrentHashMap

interface AccountRepository {
  fun save(account: Account) : Account
  fun findOne(id:AccountId) : Account?
  fun findAll() : List<Account>
  fun count() : Int
  fun existsById(id:AccountId) : Boolean
}

internal class AccountRepositoryBean : AccountRepository {

  private val store = ConcurrentHashMap<AccountId, Account>()

  override fun save(account: Account) = account.apply {
    store[account.id] = account
  }
  override fun findOne(id: AccountId) = store.getOrDefault(id, null)

  override fun findAll(): List<Account> = store.values.toList()

  override fun count(): Int = store.size

  override fun existsById(id: AccountId) = findOne(id) != null

}

data class Account(
  val id: AccountId,
  val balance: Int
) {

  fun changeBalance(diff: Int) : Account = copy(balance = balance + diff)
}
