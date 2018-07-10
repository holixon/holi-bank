package de.holisticon.bank.domain

import de.holisticon.bank.AccountId

sealed class CurrentBalance(open val id: AccountId) {

  data class Query(override val id :AccountId) : CurrentBalance(id)
  data class Result(override val id: AccountId, val balance: Int) : CurrentBalance(id)

}
