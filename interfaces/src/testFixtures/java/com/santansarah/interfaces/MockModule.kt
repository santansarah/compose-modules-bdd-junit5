package com.santansarah.interfaces

import org.koin.dsl.module

val networkLogicApiMocks = module {
    single<ICityApi> {
        MockCityApi()
    }
}
