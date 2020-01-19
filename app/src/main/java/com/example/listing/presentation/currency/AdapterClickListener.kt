package com.example.listing.presentation.currency

import com.example.listing.core.domain.Currency
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AdapterClickListener @Inject constructor(): CurrencyAdapter.Listener {

    val onCurrencySelected = PublishSubject.create<Currency>()

    override fun onClick(currency: Currency) {
        onCurrencySelected.onNext(currency)
    }

}