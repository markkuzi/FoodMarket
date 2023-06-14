package com.example.data.localBd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_data_table")
data class BasketModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "basket_id")
    val id:Int,

    @ColumnInfo(name = "basket_name")
    val name:String,

    @ColumnInfo(name = "basket_price")
    val price:Int,

    @ColumnInfo(name = "basket_weight")
    val weight:Int,

    @ColumnInfo(name = "basket_image")
    val image:String,

    @ColumnInfo(name = "basket_count")
    val count:Int

)
