package de.holisticon.holibank.backend.core.api

import java.util.*

/**
 * Value object for account id.
 */
inline class AccountId(val id: String) {
  companion object {
    operator fun invoke() = AccountId(UUID.randomUUID().toString())
  }
}
