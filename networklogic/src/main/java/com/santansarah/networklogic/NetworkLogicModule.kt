package com.santansarah.networklogic

import com.santansarah.interfaces.ICityApi
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkLogicApi = module {
    factoryOf(::CityApi) bind ICityApi::class
}
