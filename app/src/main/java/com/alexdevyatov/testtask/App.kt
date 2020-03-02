package com.alexdevyatov.testtask

import android.app.Application
import com.alexdevyatov.testtask.di.AppComponent
import com.alexdevyatov.testtask.di.DaggerAppComponent
import com.alexdevyatov.testtask.di.NetModule

class App : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .netModule(NetModule())
            .build()
    }

}