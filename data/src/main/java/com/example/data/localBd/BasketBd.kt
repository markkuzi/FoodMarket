package com.example.data.localBd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BasketModel::class], version = 1)
abstract class BasketBd: RoomDatabase() {
    abstract val basketDao : BasketDao
}