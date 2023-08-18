package com.santansarah.domainmodels
import com.santansarah.dtos.CityDTO
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CityDTOTests {
    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .build()
    }

    private lateinit var cityDTO: CityDTO

    @BeforeEach
    fun setup() {
        cityDTO = moshi.adapter(CityDTO::class.java)
            .fromJson(readJsonToString("city_dto.json"))!!
    }

    @Test
    fun `then it has parsed`() {
        cityDTO.zip.shouldNotBeNull()
        cityDTO.lat.shouldNotBeNull()
        cityDTO.lng.shouldNotBeNull()
        cityDTO.city.shouldNotBeNull()
    }
}

fun Any.readJsonToString(fileName: String): String =
    javaClass.getResource(fileName)?.readText() ?: throw NullPointerException("File $fileName not found")

inline fun <reified T> readJsonToStringSpek(fileName: String): String =
    T::class.java.getResource(fileName)?.readText() ?: throw NullPointerException("File $fileName not found")