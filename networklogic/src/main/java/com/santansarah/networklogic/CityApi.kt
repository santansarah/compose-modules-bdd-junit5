package com.santansarah.networklogic

import com.santansarah.dtos.CityDTO
import com.santansarah.interfaces.CityApiError
import com.santansarah.interfaces.ICityApi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response
import java.io.IOException

class CityApi(val moshi: Moshi) : ICityApi {

    override fun getCityList(): Observable<List<CityDTO>> {
        val client = OkHttpClient()
        val url = "http://localcityhost:8080/cities?name=new%20york"
        val request: Request = Builder()
            .url(url)
            .header("x-api-key", "Pr67HTHS4VIP1eN")
            .build()

        val jsonAdapter: JsonAdapter<List<CityDTO>> = moshi.adapter(
           Types.newParameterizedType(List::class.java, CityDTO::class.java)
        )

        return Observable.defer {
            try {
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    Observable.just(response).map {
                        jsonAdapter.fromJson(it.body!!.string()) ?: CityDTO.EMPTY
                    }
                } else {
                    Observable.error(CityApiError.fromStatusCode(response.code))
                }
            } catch (e: IOException) {
                Observable.error(CityApiError.Other(e))
            } catch (e: java.lang.IllegalStateException) {
                Observable.error(CityApiError.Other(e))
            }
        }
            .subscribeOn(Schedulers.io())
    }

    override fun getCityById(): Single<CityDTO> {
        TODO("Not yet implemented")
    }

}