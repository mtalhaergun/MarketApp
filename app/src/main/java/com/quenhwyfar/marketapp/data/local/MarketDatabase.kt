package com.quenhwyfar.marketapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quenhwyfar.marketapp.data.local.dao.ProductsDao
import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity

@Database(entities = [ProductsEntity::class], version = 1)
abstract class MarketDatabase : RoomDatabase() {
    abstract fun productsDao() : ProductsDao
}