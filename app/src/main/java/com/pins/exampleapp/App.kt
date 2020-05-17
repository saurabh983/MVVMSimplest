package com.pins.exampleapp

import android.app.Application
import com.woo.groupchat.modules.networkModule
import com.woo.groupchat.modules.repositoryModules
import com.woo.groupchat.modules.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModules,
                    networkModule,
                    repositoryModules
//                    storageModules,
//                    viewModelModules,
//                    managerModules
                )
            )
        }
    }

}