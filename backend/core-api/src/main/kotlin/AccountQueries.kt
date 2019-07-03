package de.holisticon.holibank.backend.core.api

sealed class AccountQuery(open val id: AccountId) {

  sealed class CurrentBalance(override val id: AccountId) : AccountQuery(id) {

    data class Query(override val id: AccountId) : CurrentBalance(id)
    data class Result(override val id: AccountId, val balance: Int) : CurrentBalance(id)

  }

}
//internal fun CurrentBalance.Query.subscribe(queryGateway: QueryGateway) =  with(queryGateway
//  .subscriptionQuery(this, CurrentBalance.Result::class.java, CurrentBalance.Result::class.java)) {
//  initialResult().concatWith(updates())
//}
//
//internal fun CurrentBalance.Query.send(queryGateway: QueryGateway) = queryGateway.query(this, CurrentBalance.Result::class.java)
