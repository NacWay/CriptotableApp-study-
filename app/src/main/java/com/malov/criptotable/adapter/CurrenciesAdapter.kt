package com.malov.criptotable.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.malov.criptotable.R
import com.malov.criptotable.databinding.RecyclerViewItemBinding


private lateinit var binding: RecyclerViewItemBinding

class CurrenciesAdapter : BaseAdapter<CurrenciesAdapter.CurrencyViewHolder>() {



    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,
             parent, false)
        return CurrencyViewHolder(v)
    }
    //реализация вьюхолдера
    class CurrencyViewHolder(view: View) : BaseAdapter.BaseViewHolder(view) {
        init {
//слушатель клика по элементам списка
            itemView.setOnClickListener {
            }
        }

        //привязываем элементы представления списка к RecyclerView и заполняем данными
        override fun bind(item: Any) {
            let {
                item as Currency
                Glide.with(view.context).load(item.image).into(binding.ivCurrencyIcon)
                binding.tvCurrencySym.text = item.symbol
                binding.tvCurrencyName.text = item.name
                binding.tvCurrencyMarketCap.text = item.marketCap
                binding.tvCurrencyPrice.text = item.price.toString()
            }
        }
    }

    //класс данных для элемента списка
    data class Currency(
        val id: String,
        val symbol: String,
        val name: String,
        val image: String,
        val price: Float,
        val marketCap: String,
        val marketCapRank: Int,
        val totalVolume: Float,
        val priceChangePercentage24h: Float,
        val marketCapChangePercentage24h: Float,
        val circulatingSupply: Double,
        val totalSupply: Long,
        val ath: Float,
        val athChangePercentage: Float
    )
}