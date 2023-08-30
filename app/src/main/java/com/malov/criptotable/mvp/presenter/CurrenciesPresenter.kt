package com.malov.criptotable.mvp.presenter

import com.malov.criptotable.adapter.CurrenciesAdapter
import com.malov.criptotable.di.App
import com.malov.criptotable.mvp.contract.CurrenciesContract
import com.malov.criptotable.rest.CoinGeckoApi
import io.reactivex.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrenciesPresenter: CurrenciesContract.Presenter() {

    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        App.appComponent.inject(this)
    }

    override fun makeList() {
        view.showProgress()
//1212
        //подписываемся на поток данных
        subscribe(geckoApi.getCoinMarket()
            .subscribeOn(Schedulers.io())
        //получаем данные в основном потоке
            .observeOn(AndroidSchedulers.mainThread())
        //преобразуем List<GeckoCoin> в Observable<GeckoCoin>
            .flatMap { Observable.fromIterable(it) }
        //наполняем поля элемента списка для адаптера
            .doOnNext {
                view.addCurrency(
                    CurrenciesAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
            }
            //вызывается при вызове onComplete
            .doOnComplete {
                view.hideProgress()
            }
            //подписывает Observer на Observable
            .subscribe({
                view.hideProgress()
                view.notifyAdapter()
            }, {
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            })
        )
    }

    override fun refreshList() {
        view.refresh()
        makeList()
    }
}