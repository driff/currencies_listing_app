package com.example.listing.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ListingDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "listing.db"

        fun create(context: Context): ListingDatabase =
            Room.databaseBuilder(context, ListingDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }

    abstract fun userDao(): UserDao

}