package com.example.my_whishlist_app.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase:RoomDatabase() {
    abstract fun wishDao() :WishDao
}