package com.example.listing.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(@PrimaryKey val email: String,
                      @ColumnInfo(name = "password") val password: String,
                      @ColumnInfo(name = "name") val name: String)
