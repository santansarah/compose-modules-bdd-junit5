package com.santansarah.interfaces

import com.santansarah.dtos.CityDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ICityApi {
    fun getCityList(): Observable<List<CityDTO>>
    fun getCityById(): Single<CityDTO>

}