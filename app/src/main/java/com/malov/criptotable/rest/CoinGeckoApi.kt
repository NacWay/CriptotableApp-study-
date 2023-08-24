package com.malov.criptotable.rest

import android.database.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Currency

interface CoinGeckoApi {
    //запрос списка
    @GET("coins/markets")
    fun getCoinMarket(
        @Query("vs_currency") vs: String = "usd",
        @Query("per_page") perPage: Int = 100,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("order") order: String = "market_cap_desc"
    ) : Observable<List<GeckoCoin>>

    //запрос данных для графика
    @GET("coins/{id}/market_chart")
    fun getCoinMarketChart(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "max"
    ) : Observable<GeckoCoinChart>

}