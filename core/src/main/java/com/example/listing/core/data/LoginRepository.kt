package com.example.listing.core.data

import io.reactivex.Single

class LoginRepository (private val datasource: UserDatasource) {

    fun addUser(email: String, password: String, name: String): String = datasource.addUser(email, password, name)

    fun verifyUser(email: String, password: String): Single<Boolean> = datasource.verifyUser(email, password)

}