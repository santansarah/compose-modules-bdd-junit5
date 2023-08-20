package com.santansarah.domainmodels
import com.santansarah.dtos.CityApiResponseDTO
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBeEqualTo
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

    private lateinit var cityDTO: CityApiResponseDTO

    @BeforeEach
    fun setup() {
        cityDTO = moshi.adapter(CityApiResponseDTO::class.java)
            .fromJson(readJsonToString("city_dto.json"))!!

    }

    @Test
    fun `then it has parsed`() {

        cityDTO.cities.count().shouldBeEqualTo(5)
        cityDTO.cities[0].zip.shouldNotBeNull()
    }
}

fun Any.readJsonToString(fileName: String): String =
    javaClass.getResource(fileName)?.readText() ?: throw NullPointerException("File $fileName not found")

inline fun <reified T> readJsonToStringSpek(fileName: String): String =
    T::class.java.getResource(fileName)?.readText() ?: throw NullPointerException("File $fileName not found")