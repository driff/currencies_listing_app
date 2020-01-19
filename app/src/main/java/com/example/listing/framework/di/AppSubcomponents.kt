package com.example.listing.framework.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ActivityComponent::class])
object AppSubcomponents {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("listingApp", Context.MODE_PRIVATE)
    }

}