package de.holisticon.bank.domain

import de.holisticon.bank.AccountId
import org.axonframework.queryhandling.QueryGateway

sealed class CurrentBalance(open val id: AccountId) {

  data class Query(override val id :AccountId) : CurrentBalance(id)
  data class Result(override val id: AccountId, val balance: Int) : CurrentBalance(id)

}

internal fun CurrentBalance.Query.subscribe(queryGateway: QueryGateway) =  with(queryGateway
  .subscriptionQuery(this, CurrentBalance.Result::class.java, CurrentBalance.Result::class.java)) {
  initialResult().concatWith(updates())
}

internal fun CurrentBalance.Query.send(queryGateway: QueryGateway) = queryGateway.query(this, CurrentBalance.Result::class.java)
