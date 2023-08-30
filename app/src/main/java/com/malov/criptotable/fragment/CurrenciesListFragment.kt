package com.malov.criptotable.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.malov.criptotable.R
import com.malov.criptotable.adapter.BaseAdapter
import com.malov.criptotable.adapter.CurrenciesAdapter
import com.malov.criptotable.di.App
import com.malov.criptotable.mvp.contract.CurrenciesContract
import com.malov.criptotable.mvp.presenter.CurrenciesPresenter
import javax.inject.Inject

class CurrenciesListFragment : BaseListFragment(), CurrenciesContract.View {
    @Inject
    lateinit var presenter: CurrenciesPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currencies_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)
        presenter.makeList()
    }
    override fun createAdapterInstance(): BaseAdapter<*> {
        return CurrenciesAdapter()
    }
    override fun addCurrency(currency: CurrenciesAdapter.Currency) {
        viewAdapter.add(currency)
    }
    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }
    override fun showProgress() {
        requireActivity().progress.visibility = View.VISIBLE
    }
    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }
    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
        }
    }