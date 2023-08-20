package com.santansarah.dtos
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityApiResponseDTO(
    @Transient val userWithApp: Any? = null,
    val cities: List<CityDTO> = emptyList(),
    val errors: List<ResponseErrorsDTO> = emptyList()
) {
    companion object {
        val EMPTY = CityApiResponseDTO()
    }
}

@JsonClass(generateAdapter = true)
data class CityDTO(
    val zip: Int = 0,
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val city: String = "",
    val state: String = "",
    val population: Int = 0,
    val county: String = ""
) {
    companion object {
        val EMPTY = emptyList<CityDTO>()
    }
}
@JsonClass(generateAdapter = true)
data class ResponseErrorsDTO(val code: String, val message: String)