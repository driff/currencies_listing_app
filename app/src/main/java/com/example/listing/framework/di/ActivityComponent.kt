package com.example.listing.framework.di

import com.example.listing.presentation.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [RepositoriesModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(mainActivity: MainActivity)
    fun fragmentComponent(): FragmentsComponent.Factory
}