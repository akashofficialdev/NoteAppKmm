package com.quokkalabs.noteappkmm.android

import com.quokkalabs.noteappkmm.cache.AndroidDatabaseDriverFactory
import com.quokkalabs.noteappkmm.network.SpaceXApi
import com.quokkalabs.noteappkmm.SpaceXSDK
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single<SpaceXApi> { SpaceXApi() }
    single<SpaceXSDK> {
        SpaceXSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(
                androidContext()
            ), api = get()
        )
    }

    viewModel { RocketLaunchViewModel(sdk = get()) }
}