package com.example.listing.framework.di

import android.content.Context
import com.example.listing.framework.db.ListingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DbModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabase(context: Context): ListingDatabase = ListingDatabase.create(context)

}