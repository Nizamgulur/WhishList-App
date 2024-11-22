package com.example.my_whishlist_app

import android.content.Context
import androidx.room.Room
import com.example.my_whishlist_app.data.WishDatabase
import com.example.my_whishlist_app.data.Wishrespository

object Graph {
    lateinit var database:WishDatabase
    val wishRepository by lazy {
        //Lazy delays the initialization to when it is required
        Wishrespository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java,"wishList.db").build()
    }
}