package com.santansarah.dtos
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityDTO(
    val zip: Int = 0,
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val city: String = "",
    val state: String = "",
    val population: Int = 0,
    val county: String = ""
)