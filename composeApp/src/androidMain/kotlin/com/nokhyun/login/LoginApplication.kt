package com.nokhyun.login

import android.app.Application
import di.appModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LoginApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())

        startKoin {
            androidContext(this@LoginApplication)
            androidLogger()
            modules(appModule())
        }
    }
}