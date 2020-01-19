package com.example.listing.presentation.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listing.core.data.CurrencyRepository
import com.example.listing.core.domain.Currency
import com.example.listing.framework.di.PerActivity
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerActivity
class CurrencyViewModel @Inject constructor(val currencyRepository: CurrencyRepository): ViewModel() {

    val currencies: LiveData<List<Currency>> by lazy { LiveDataReactiveStreams.fromPublisher(currencyRepository.getCurrencies()
        .toFlowable().onErrorReturn { listOf() }.subscribeOn(Schedulers.io())) }

    val opennedCurrency: MutableLiveData<Currency> = MutableLiveData()

    fun selectedCurrency(currency: Currency) {
        opennedCurrency.postValue(currency)
    }

}
