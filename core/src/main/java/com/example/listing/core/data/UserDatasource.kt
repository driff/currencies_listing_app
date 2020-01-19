package com.example.listing.core.data

import io.reactivex.Single

interface UserDatasource {

    fun addUser(email: String, password: String, name: String): String

    fun verifyUser(email: String, password: String): Single<Boolean>

}