package com.production.sidorov.ivan.notekotlinmvp.di.component

import com.production.sidorov.ivan.notekotlinmvp.di.module.AppModule
import com.production.sidorov.ivan.notekotlinmvp.mvp.presenter.MainPresenter
import com.production.sidorov.ivan.notekotlinmvp.mvp.presenter.NotePresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)]) interface AppComponent{
    fun inject (mainPresenter: MainPresenter)
    fun inject (notePresenter: NotePresenter)
}
