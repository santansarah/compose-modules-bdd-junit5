package com.santansarah.repositories

import com.santansarah.domainmodels.City
import com.santansarah.errors.AppErrors
import com.santansarah.interfaces.CityApiError
import com.santansarah.interfaces.ICityApi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

fun Throwable.toCityListError(): AppErrors {
    return when (this) {
        is CityApiError.Forbidden -> {
            AppErrors.Forbidden
        }

        else -> {
            AppErrors.Other
        }
    }
}

// This is a push-based repository example
class CityListPushBasedRepository(private val cityApi: ICityApi) {
    private var _cities: BehaviorSubject<List<City>> = BehaviorSubject.createDefault(emptyList())
    val cities: Observable<List<City>> = _cities

    init {
        println("cityapi: $cityApi")
    }

    fun reload(): Completable {
        println("getting cities...")
        return cityApi.getCityList()
            .doOnNext { cityList ->
                println("cities: $cityList")

                this._cities.onNext(
                    listOf(
                        City(
                            zip = 45373,
                            lat = 40.03353,
                            lng = -84.19588,
                            city = "Troy",
                            state = "OH",
                            population = 35913,
                            county = "Miami"
                        ), City(
                            zip = 12180,
                            lat = 42.7516,
                            lng = -73.59997,
                            city = "Troy",
                            state = "NY",
                            population = 53181,
                            county = "Rensselaer"
                        )
                    )

//                    cityList.map { cityDTO ->
//                        City(
//                            city = cityDTO.city,
//                            zip = cityDTO.zip,
//                            lat = cityDTO.lat,
//                            lng = cityDTO.lng,
//                            state = cityDTO.state,
//                            population = cityDTO.population,
//                            county = cityDTO.county
//                        )
//                    }
                )
            }
            .ignoreElements()
            .onErrorResumeNext {
                Completable.error(it.toCityListError())
            }
    }

}