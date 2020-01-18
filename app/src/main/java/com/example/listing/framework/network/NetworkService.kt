package com.example.listing.framework.network

import com.example.listing.core.domain.Mindicador
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET("api/v1/counters")
    fun fetchCounters(): Single<Mindicador>

}