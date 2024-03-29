package com.trickring.app_mvvm_usecase

import android.app.Application
import com.trickring.app_mvvm_usecase.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * [Application] subclass.
 */
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDependencyInjection()
        initializeLogging()
    }

    /**
     * Initialize Dependency Injection Library.
     */
    private fun initializeDependencyInjection() {
        startKoin {
            androidContext(this@App)
            modules(AppModule.instance)
        }
    }

    /**
     * Initialize Logging Library.
     */
    private fun initializeLogging() {
        Timber.plant(Timber.DebugTree())
    }
}
