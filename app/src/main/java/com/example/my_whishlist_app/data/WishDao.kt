package com.example.my_whishlist_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)//On conflict takes care of duplicate entries as pef the defined action
    abstract suspend fun addAWish(wishEntity:Wish)//Wish is now a Entity as defined in the data class

    @Query("Select * from 'wish-table'")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteAWish(wishEntity: Wish)

    @Query("Select * from 'wish-table' where id=:id")
    abstract fun getAWishbyId(id:Long): Flow<Wish>

}