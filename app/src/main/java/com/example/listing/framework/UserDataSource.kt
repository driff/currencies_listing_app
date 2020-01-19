package com.example.listing.framework

import android.util.Log
import com.example.listing.core.data.CryptoRepository
import com.example.listing.core.data.UserDatasource
import com.example.listing.core.domain.User
import com.example.listing.framework.db.ListingDatabase
import com.example.listing.framework.db.UserEntity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDataSource @Inject constructor(private val db: ListingDatabase, private val cryptoRepository: CryptoRepository): UserDatasource {

    private fun getUser(email: String): Single<UserEntity> = db.userDao().getUser(email)

    override fun addUser(email:String, password: String, name: String): Single<User> {
        val encryptedPassword = cryptoRepository.encryptString(password)
        if(!encryptedPassword.isNullOrEmpty()) {
            return Single.just(UserEntity(email, encryptedPassword, name))
                .map {
                    db.userDao().addUser(it)
                    return@map User(it.email, it.password, it.email)
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
        Log.d("LoginDatasource", "Error, password not encrypted")
        return Single.just(null)
    }

    override fun verifyUser(email: String, password: String): Single<User> {
        val encryptedPassword = cryptoRepository.encryptString(password)
        return getUser(email).map {
            Log.d("UserDatasource", "getUser: $it")
            if (it.password == encryptedPassword) {
                return@map User(it.email, it.password, it.name)
            }
            throw Error("INVALID_USER")
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}