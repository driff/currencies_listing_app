package com.example.listing.core.data

import com.example.listing.core.domain.Mindicador

class CurrencyRepository (private val dataSource: CurrencyDataSource) {

    fun addCurrencies(mindicador: Mindicador) = dataSource.add(mindicador)

    fun getCurrencies() = dataSource.read()

}