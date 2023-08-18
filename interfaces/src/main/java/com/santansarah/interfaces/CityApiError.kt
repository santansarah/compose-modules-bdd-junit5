package com.santansarah.interfaces

import java.net.HttpURLConnection

sealed class CityApiError(val statusCode: Int? = null) : Throwable() {
    object CityNotFound : CityApiError(
        HttpURLConnection.HTTP_NOT_FOUND
    )

    object Forbidden : CityApiError(
        HttpURLConnection.HTTP_FORBIDDEN
    )

    // These two cases cannot be pulled into a superclass, otherwise we could not enumerate
    // all possible cases of SmsSendError
    //
    // Swift allows us to use generics with the `Error` protocol; Java prohibits us from using
    // generic subclass of Throwable:
    // https://stackoverflow.com/questions/501277/
    data class CommonError(
        val apiResponseCode: ApiResponseCode,
    ) : CityApiError(apiResponseCode.statusCode)

    data class Other(val throwable: Throwable) : CityApiError()

    companion object {
        private fun domainErrorFromStatusCode(statusCode: Int): CityApiError? {
            // https://ivanmorgillo.com/2020/03/11/can-i-loop-over-a-kotlin-sealed-class/
            return CityApiError::class.sealedSubclasses
                .firstOrNull { it.objectInstance?.statusCode == statusCode }
                ?.objectInstance
        }

        fun fromStatusCode(statusCode: Int): CityApiError {
            return domainErrorFromStatusCode(statusCode) ?: CommonError(
                ApiResponseCode.fromStatusCode(statusCode)
            )
        }
    }
}