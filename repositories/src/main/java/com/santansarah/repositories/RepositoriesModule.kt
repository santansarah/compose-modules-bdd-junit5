package com.santansarah.repositories

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoriesModule = module {
    singleOf(::CityListPushBasedRepository)
}
