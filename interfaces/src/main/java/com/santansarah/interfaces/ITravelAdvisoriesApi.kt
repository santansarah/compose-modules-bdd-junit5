package com.example.interfaces
import com.santansarah.dtos.CityDTO
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ITravelAdvisoriesApi {
    fun getForbiddenApi(): Completable
    fun getCountryList(): Observable<CountryListDTO>
    fun getCountryDetails(regionCode: String): Single<CityDTO>
    fun getServerStatus(): Observable<ServerStatusDTO>
}