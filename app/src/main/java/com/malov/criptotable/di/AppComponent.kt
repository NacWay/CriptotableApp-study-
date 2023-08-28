package com.malov.criptotable.di

import com.malov.criptotable.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(
    AppModule::class,
    RestModule::class,
    MvpModule::class,
    ChartModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}