package com.example.listing.framework.di

import com.example.listing.presentation.currency.CurrenciesFragment
import com.example.listing.presentation.currency.CurrencyDetailsFragment
import com.example.listing.presentation.login.LoginFragment
import com.example.listing.presentation.login.SignupFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [AdapterListener::class])
interface FragmentsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentsComponent
    }

    fun inject(fragment: LoginFragment)
    fun inject(fragment: SignupFragment)
    fun inject(fragment: CurrenciesFragment)
    fun inject(fragment: CurrencyDetailsFragment)
}