package com.example.data.localBd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basketModel: BasketModel)

    @Update
    fun update(basketModel: BasketModel)

    @Query("SELECT * FROM basket_data_table")
    fun getBasket(): Flow<List<BasketModel>>

    @Query("DELETE FROM basket_data_table WHERE basket_id = :idBasketDish")
    suspend fun deleteDishFromBasket(idBasketDish:Int)

    @Query("DELETE FROM basket_data_table")
    suspend fun clear()
}