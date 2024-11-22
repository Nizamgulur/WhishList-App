package com.example.my_whishlist_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="wish-table")//define the rows
data class Wish(
    @PrimaryKey(autoGenerate = true)//A unique value to identify the data in the table and gets auto incremented
    val id:Long = 0L,
    @ColumnInfo("wish-title")//name of the column in table created
    val title:String = "",
    @ColumnInfo("wish-desc")
    val description:String = ""

)

