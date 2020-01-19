package com.example.listing.core.data

import com.example.listing.core.domain.Currency
import io.reactivex.Single

interface CurrencyDataSource {

    fun read(): Single<List<Currency>>

}