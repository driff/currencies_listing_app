package com.example.listing.core.data

import com.example.listing.core.domain.User
import io.reactivex.Single

interface UserDatasource {

    fun addUser(email: String, password: String, name: String): Single<User>

    fun verifyUser(email: String, password: String): Single<User>

}