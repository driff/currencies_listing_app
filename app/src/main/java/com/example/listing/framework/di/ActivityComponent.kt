package com.example.listing.framework.di

import com.example.listing.presentation.MainActivity
import com.example.listing.presentation.currency.CurrenciesFragment
import com.example.listing.presentation.currency.CurrencyDetailsFragment
import com.example.listing.presentation.login.LoginFragment
import com.example.listing.presentation.login.SignupFragment
import com.example.listing.presentation.login.recovery.RecoverPasswordFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [RepositoriesModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: SignupFragment)
    fun inject(fragment: CurrenciesFragment)
    fun inject(fragment: CurrencyDetailsFragment)
    fun inject(fragment: RecoverPasswordFragment)
}