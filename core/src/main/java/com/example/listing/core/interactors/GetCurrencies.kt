package com.example.listing.core.interactors

import com.example.listing.core.data.CurrencyRepository

class GetCurrencies (private val currencyRepository: CurrencyRepository) {
    operator fun invoke() = currencyRepository.getCurrencies()
}