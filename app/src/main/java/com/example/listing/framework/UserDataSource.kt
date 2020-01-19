package com.example.listing.framework

import android.util.Log
import com.example.listing.core.data.CryptoRepository
import com.example.listing.core.data.UserDatasource
import com.example.listing.framework.db.ListingDatabase
import com.example.listing.framework.db.UserEntity
import io.reactivex.Single
import javax.inject.Inject

class UserDataSource @Inject constructor(private val db: ListingDatabase, private val cryptoRepository: CryptoRepository): UserDatasource {

    private fun getUser(email: String): Single<UserEntity> = db.userDao().getUser(email)

    override fun addUser(email:String, password: String, name: String): String {
        val encryptedPassword = cryptoRepository.encryptString(password)
        if(encryptedPassword.isNullOrEmpty()) {
            //TODO: Show error message
            Log.d("LoginDatasource", "Error, password not encrypted")
        } else {
            db.userDao().addUser(UserEntity(email, encryptedPassword, name))
            return encryptedPassword
        }
        return ""
    }

    override fun verifyUser(email: String, password: String): Single<Boolean> {
        val encryptedPassword = cryptoRepository.encryptString(password)
        return getUser(email).map {
            return@map it.password == encryptedPassword
        }
    }

}