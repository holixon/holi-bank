package de.holisticon.bank.domain

import de.holisticon.bank.AccountId

sealed class HoliBankException(msg:String) : RuntimeException(msg)

class InsufficientBalance(balance: Int, withdrawal: Int) : HoliBankException("Insufficient balance:$balance, withdrawal:$withdrawal.")
class AccountNotExistent(id: AccountId) : HoliBankException("Account: $id does not exist.")


