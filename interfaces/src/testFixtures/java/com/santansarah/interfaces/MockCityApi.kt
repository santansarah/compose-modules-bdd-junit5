package com.santansarah.interfaces

import com.santansarah.dtos.CityDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MockCityApi : ICityApi {

    override fun getCityById(): Single<CityDTO> {
        TODO("Not yet implemented")
    }

    private var getCityListResult: Observable<List<CityDTO>>? = null
    override fun getCityList(): Observable<List<CityDTO>> {
        getCityListResult?.let {
            return it
        } ?: run {
            return Observable.just(
                listOf(
                    CityDTO(
                        zip = 45373,
                        lat = 40.03353,
                        lng = -84.19588,
                        city = "Troy",
                        state = "OH",
                        population = 35913,
                        county = "Miami"
                    ), CityDTO(
                        zip = 12180,
                        lat = 42.7516,
                        lng = -73.59997,
                        city = "Troy",
                        state = "NY",
                        population = 53181,
                        county = "Rensselaer"
                    )
                )
            )
                .observeOn(Schedulers.computation())
        }
    }

}