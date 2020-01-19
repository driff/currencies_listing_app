package com.example.listing.core.data

import com.example.listing.core.domain.Mindicador

class CurrencyRepository (private val dataSource: CurrencyDataSource) {

    fun getCurrencies() = dataSource.read()

}