package com.santansarah.errors

sealed class AppErrors : Throwable() {
    object Forbidden : AppErrors()
    object ConnectionError : AppErrors()
    object Other : AppErrors()
    object UserNotLoggedIn : AppErrors()

    object NotEnoughPermissionsError : AppErrors()
    object NotAvailableError : AppErrors()
    data class BlockedCountry(val reason: String) : AppErrors()

    object InternalError : AppErrors()
}