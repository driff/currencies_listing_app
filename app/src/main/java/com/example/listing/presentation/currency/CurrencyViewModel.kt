package com.example.listing.presentation.currency

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listing.core.data.CurrencyRepository
import com.example.listing.core.domain.Currency
import com.example.listing.framework.di.PerActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerActivity
class CurrencyViewModel @Inject constructor(val currencyRepository: CurrencyRepository) :
    ViewModel() {

    private val TAG = this.javaClass.canonicalName
    val disposable = CompositeDisposable()

    val currencies: LiveData<List<Currency>> by lazy {
        LiveDataReactiveStreams.fromPublisher(currencyRepository.getCurrencies()
            .toFlowable().onErrorReturn { listOf() }.subscribeOn(Schedulers.io())
        )
    }
    val filteredCurrencies: MutableLiveData<List<Currency>> = MutableLiveData()
    val opennedCurrency: MutableLiveData<Currency> = MutableLiveData()

    fun selectedCurrency(currency: Currency) {
        opennedCurrency.postValue(currency)
    }

    fun filterCurrencies(text: CharSequence?) {
        if (!currencies.value.isNullOrEmpty() && !text.isNullOrEmpty()) {
            disposable.add(
                Observable.just(currencies.value!!)
                    .flatMap { Observable.fromIterable(it) }
                    .filter { currency -> currency.codigo.contains(text) }
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        filteredCurrencies.postValue(it)
                    }, {
                        Log.e(TAG, "Error in filter", it)
                    })
            )
        }else {
            filteredCurrencies.postValue(currencies.value)
        }
    }

}
