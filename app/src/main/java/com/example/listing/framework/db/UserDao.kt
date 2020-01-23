package com.example.listing.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun addUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE email is :email")
    fun getUser(email: String): Single<UserEntity>

    @Update
    fun updateUser(updatedUser: UserEntity)

}