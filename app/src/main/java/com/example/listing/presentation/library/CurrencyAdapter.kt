package com.example.listing.presentation.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listing.R
import com.example.listing.core.domain.Currency
import kotlinx.android.synthetic.main.item_currency.view.*
import javax.inject.Inject

class CurrencyAdapter @Inject constructor(private val currencies: List<Currency> = listOf(), private val itemClickListener: (Currency) -> Unit): RecyclerView.Adapter<CurrencyAdapter.ViewHolder>(){

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
        holder.itemView.setOnClickListener { itemClickListener.invoke(currencies[position]) }
    }

}