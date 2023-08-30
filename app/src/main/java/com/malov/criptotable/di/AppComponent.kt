package com.malov.criptotable.di

import com.malov.criptotable.MainActivity
import com.malov.criptotable.fragment.CurrenciesListFragment
import com.malov.criptotable.mvp.presenter.LatestChartPresenter
import com.malov.criptotable.mvp.presenter.CurrenciesPresenter

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
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)
}
