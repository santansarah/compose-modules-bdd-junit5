package com.santansarah.viewmodels

import androidx.lifecycle.ViewModel
import com.santansarah.domainmodels.City
import com.santansarah.errors.AppErrors
import com.santansarah.repositories.CityListPushBasedRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CityListViewModel(val repo: CityListPushBasedRepository) : ViewModel() {
    data class UiState(
        val cities: List<City> = emptyList(),
        val isLoading: Boolean = false,
        val isLoaded: Boolean = false,
        val error: AppErrors? = null
    )

    private val _state: BehaviorSubject<UiState> = BehaviorSubject.createDefault(UiState())
    val state: Observable<UiState> = _state
    private val disposables = CompositeDisposable()

    init {
        // https://stackoverflow.com/questions/73305899/why-launchedeffect-call-second-time-when-i-navigate-back
        onPageLoaded()
    }

    private fun onPageLoaded() {
        disposables.add(repo.cities.doOnNext {
            _state.onNext(_state.value!!.copy(cities = it))
        }.subscribe())

        disposables.add(
            repo.reload()
                .doOnSubscribe {
                    _state.onNext(_state.value!!.copy(isLoading = true))
                }
                .subscribe({
                    _state.onNext(_state.value!!.copy(isLoading = false, isLoaded = true))
                }, { error ->
                    _state.onNext(_state.value!!.copy(
                        isLoading = false,
                        isLoaded = true,
                        error = (error as AppErrors)
                    ))
                })
        )

    }

    fun dismissError() {
        _state.onNext(_state.value!!.copy(error = null))
    }

    private fun emitError(error: Throwable) {
        when (val countryListError = error as AppErrors) {
            is AppErrors.NotEnoughPermissionsError -> {
                _state.onNext(_state.value!!.copy(error = countryListError))
            }
            else -> {
                _state.onNext(_state.value!!.copy(error = countryListError))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        disposables.dispose()
    }
}