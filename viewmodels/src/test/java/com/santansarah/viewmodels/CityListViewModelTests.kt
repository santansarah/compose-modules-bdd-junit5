package com.santansarah.viewmodels

import com.santansarah.interfaces.networkLogicApiMocks
import com.santansarah.repositories.repositoriesModule
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.concurrent.TimeUnit

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CityListViewModelTests: KoinTest {
    @BeforeEach
    fun setup() {
        startKoin {
            loadKoinModules(viewModelModule + repositoriesModule + networkLogicApiMocks)
        }

        testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        stateTestObserver = viewModel.state.test()
    }

    @AfterEach
    fun cleanup() {
        stopKoin()
        RxJavaPlugins.setComputationSchedulerHandler(null)
    }

    private val viewModel: CityListViewModel by inject()
    private lateinit var testScheduler: TestScheduler
    lateinit var stateTestObserver: TestObserver<CityListViewModel.UiState>

    @Test
    fun `then it starts having transitioned to loading`() {
        stateTestObserver.values().shouldBeEqualTo(listOf(
            CityListViewModel.UiState(isLoading = true)
        ))
    }

    @Nested
    @DisplayName("when I advance")
    inner class Advance {
        @BeforeEach
        fun setup() {
            testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        }

        @Test
        fun `then the middle emission is still loading`() {
            println("values: " + stateTestObserver.values())
            stateTestObserver.values()[1].apply {
                isLoaded.shouldBeFalse()
                isLoading.shouldBeTrue()
                cities.isNotEmpty().shouldBeTrue()
            }
        }

        @Test
        fun `then the final emission has loaded`() {
            stateTestObserver.values()[2].apply {
                isLoaded.shouldBeTrue()
                isLoading.shouldBeFalse()
                cities.isNotEmpty().shouldBeTrue()
            }
        }
    }
}