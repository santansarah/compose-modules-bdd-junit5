package com.santansarah.repositories

import com.santansarah.domainmodels.City
import com.santansarah.interfaces.networkLogicApiMocks
import io.reactivex.rxjava3.observers.TestObserver
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
open class CityListLogicTests: KoinTest {
    @BeforeEach
    open fun setup() {
        startKoin {
            loadKoinModules(repositoriesModule + networkLogicApiMocks)
        }
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    val countryListRepository: CityListPushBasedRepository by inject()

    @Nested
    @DisplayName("#init")
    inner class GetDetails {
        lateinit var testObserver: TestObserver<List<City>>
        lateinit var value: List<City>

        @BeforeEach
        fun setup() {
            testObserver = countryListRepository.cities.test()
            value = testObserver.values().last()
        }

        @Test
        fun `then it should be empty`() {
            value.shouldBeEmpty()
        }

        @Nested
        @DisplayName("#reload")
        inner class Reload {
            private lateinit var reloadObserver: TestObserver<Void>

            @BeforeEach
            fun setup() {
                reloadObserver = countryListRepository.reload().test()
                testObserver.awaitCount(2)
                value = testObserver.values().last()
            }

            @Test
            fun `then it should be valid`() {
                value.count().shouldBeEqualTo(2)
            }
        }
    }
}