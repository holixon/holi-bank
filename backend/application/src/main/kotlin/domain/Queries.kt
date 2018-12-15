package de.holisticon.bank.domain

import org.axonframework.queryhandling.QueryGateway


internal fun CurrentBalance.Query.subscribe(queryGateway: QueryGateway) =  with(queryGateway
  .subscriptionQuery(this, CurrentBalance.Result::class.java, CurrentBalance.Result::class.java)) {
  initialResult().concatWith(updates())
}

internal fun CurrentBalance.Query.send(queryGateway: QueryGateway) = queryGateway.query(this, CurrentBalance.Result::class.java)
