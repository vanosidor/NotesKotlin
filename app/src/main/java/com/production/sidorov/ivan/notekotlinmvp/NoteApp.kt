package com.production.sidorov.ivan.notekotlinmvp

import android.app.Application
import com.production.sidorov.ivan.notekotlinmvp.di.component.AppComponent
import com.production.sidorov.ivan.notekotlinmvp.di.component.DaggerAppComponent
import com.production.sidorov.ivan.notekotlinmvp.di.module.AppModule


class NoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        component =   DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var component: AppComponent
    }

}
