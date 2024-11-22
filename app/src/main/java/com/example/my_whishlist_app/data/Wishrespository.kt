package com.example.my_whishlist_app.data

import kotlinx.coroutines.flow.Flow

class Wishrespository(private val wishDao:WishDao) {

    suspend fun addAWish(wish:Wish){
        wishDao.addAWish(wish)
    }

    fun getWhishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAwishById(id:Long): Flow<Wish> = wishDao.getAWishbyId(id)

    suspend fun updateAWish(wish:Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish:Wish){
        wishDao.deleteAWish(wish)
    }
}