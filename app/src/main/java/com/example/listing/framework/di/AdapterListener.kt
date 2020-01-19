package com.example.listing.framework.di

import com.example.listing.presentation.currency.AdapterClickListener
import com.example.listing.presentation.currency.CurrenciesFragment
import com.example.listing.presentation.currency.CurrencyAdapter
import dagger.Binds
import dagger.Module

@Module
abstract class AdapterListener{

    @PerFragment
    @Binds
    abstract fun provideListener(listener: AdapterClickListener): CurrencyAdapter.Listener

}