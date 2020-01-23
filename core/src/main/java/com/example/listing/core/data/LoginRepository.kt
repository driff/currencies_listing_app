package com.example.listing.core.data

import com.example.listing.core.domain.User
import io.reactivex.Single

class LoginRepository (private val datasource: UserDatasource) {

    fun addUser(email: String, password: String, name: String): Single<User> = datasource.addUser(email, password, name)

    fun verifyUser(email: String, password: String): Single<User> = datasource.verifyUser(email, password)

    fun recoverPassword(email: String, password: String): Single<User> = datasource.recoverPassword(email, password)

}