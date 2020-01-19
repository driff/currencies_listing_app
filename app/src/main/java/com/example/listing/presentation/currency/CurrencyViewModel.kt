package com.example.listing.presentation.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.listing.core.data.CurrencyRepository
import com.example.listing.core.domain.Currency
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(val currencyRepository: CurrencyRepository): ViewModel() {

    val currencies: LiveData<List<Currency>> by lazy { LiveDataReactiveStreams.fromPublisher(currencyRepository.getCurrencies()
        .toFlowable().onErrorReturn { listOf() }.subscribeOn(Schedulers.io())) }

}