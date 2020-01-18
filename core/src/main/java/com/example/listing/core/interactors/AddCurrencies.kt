package com.example.listing.core.interactors

import com.example.listing.core.data.CurrencyRepository
import com.example.listing.core.domain.Mindicador

class AddCurrencies (private val currencyRepository: CurrencyRepository) {
    operator fun invoke(mindicador: Mindicador) = currencyRepository.addCurrencies(mindicador)
}