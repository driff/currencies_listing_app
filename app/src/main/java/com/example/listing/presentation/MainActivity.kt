package com.example.listing.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listing.R
import com.example.listing.framework.ListingApplication
import com.example.listing.framework.di.ActivityComponent

class MainActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as ListingApplication).appComponent.activityComponent().create()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

}
