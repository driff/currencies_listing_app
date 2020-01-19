package com.example.listing.presentation.currency

import android.util.Log
import com.example.listing.core.domain.Currency
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AdapterClickListener @Inject constructor(): CurrencyAdapter.Listener {

    private val TAG = this.javaClass.canonicalName

    val onCurrencySelected = PublishSubject.create<Currency>()

    override fun onClick(currency: Currency) {
        Log.d(TAG, "click: $currency")
        onCurrencySelected.onNext(currency)
    }

}