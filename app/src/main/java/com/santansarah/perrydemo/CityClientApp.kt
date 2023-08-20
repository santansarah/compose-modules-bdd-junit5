package com.santansarah.perrydemo

import android.app.Application
import com.santansarah.dtos.dtoModelsModule
import com.santansarah.networklogic.networkLogicApi
import com.santansarah.repositories.repositoriesModule
import com.santansarah.viewmodels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CityBDDApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@CityBDDApp)
            // Load modules
            modules(viewModelModule + repositoriesModule + networkLogicApi + dtoModelsModule)
        }

    }
}