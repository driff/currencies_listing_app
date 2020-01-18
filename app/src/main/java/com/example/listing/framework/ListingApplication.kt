package com.example.listing.framework

import android.app.Application
import com.example.listing.framework.di.AppComponent
import com.example.listing.framework.di.DaggerAppComponent

class ListingApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}