package com.example.listing.core.data

import com.example.listing.core.domain.Currency
import com.example.listing.core.domain.Mindicador
import io.reactivex.Single

interface CurrencyDataSource {

    fun add(mindicador: Mindicador)

    fun read(): Single<List<Currency>>

}