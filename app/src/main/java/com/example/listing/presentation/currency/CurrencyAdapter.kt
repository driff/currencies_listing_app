package com.example.listing.presentation.currency

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listing.R
import com.example.listing.core.domain.Currency
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_currency.view.*
import javax.inject.Inject

class CurrencyAdapter @Inject constructor(): RecyclerView.Adapter<CurrencyAdapter.ViewHolder>(){

    private var currencies: List<Currency> = listOf()

    private val onCurrencySelected = PublishSubject.create<Currency>()

    fun getCurrencySelectedObs() = onCurrencySelected.toFlowable(BackpressureStrategy.LATEST)

    fun addCurrencies(list: List<Currency>) {
        currencies = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTxv: TextView = view.tvTitle
        val valueTxv: TextView = view.tvValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTxv.text = currencies[position].nombre
        holder.valueTxv.text = currencies[position].valor.toString()
        holder.itemView.setOnClickListener {
            Log.d("Adapter", "Listener")
            onCurrencySelected.onNext(currencies[position])
        }
    }

}