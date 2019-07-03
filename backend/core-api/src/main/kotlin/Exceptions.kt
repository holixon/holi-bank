package de.holisticon.holibank.backend.core.api


sealed class HoliBankException(msg:String) : RuntimeException(msg) {

  class InsufficientBalance(balance: Int, withdrawal: Int) : HoliBankException("Insufficient balance:$balance, withdrawal:$withdrawal.")
  class AccountNotExistent(id: AccountId) : HoliBankException("Account: $id does not exist.")

}
