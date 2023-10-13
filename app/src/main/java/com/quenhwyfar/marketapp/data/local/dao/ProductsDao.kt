package com.quenhwyfar.marketapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    fun getProducts() : Flow<List<ProductsEntity>>

    @Insert
    suspend fun insert(product : ProductsEntity)

    @Query("DELETE FROM products")
    suspend fun deleteAll()



}