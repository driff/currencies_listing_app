package com.example.listing.framework

import com.example.listing.core.data.CurrencyDataSource
import com.example.listing.core.domain.Currency
import com.example.listing.framework.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class CurrenciesDatasource @Inject constructor(private val networkService: NetworkService): CurrencyDataSource {

    override fun read(): Single<List<Currency>> {
        return networkService.fetchCounters()
            .map {
                val list = mutableListOf<Currency>()
                it.apply {
                    list.add(bitcoin)
                    list.add(dolar)
                    list.add(dolarIntercambio)
                    list.add(euro)
                    list.add(imacec)
                    list.add(ipc)
                    list.add(ivp)
                    list.add(libraCobre)
                    list.add(tasaDesempleo)
                    list.add(tpm)
                    list.add(uf)
                    list.add(utm)
                }
                list.toList()
            }
    }

}