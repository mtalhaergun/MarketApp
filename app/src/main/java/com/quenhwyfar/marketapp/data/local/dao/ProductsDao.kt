package com.quenhwyfar.marketapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    fun getProducts() : Flow<List<ProductsEntity>>

    @Insert
    suspend fun insert(product : ProductsEntity)

    @Delete
    suspend fun delete(product: ProductsEntity)

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    @Update
    suspend fun update(product : ProductsEntity?)

    @Query("SELECT EXISTS(SELECT 1 FROM products WHERE name = :name LIMIT 1)")
    suspend fun searchName(name: String): Boolean

    @Query("SELECT count FROM products WHERE name = :name LIMIT 1")
    suspend fun searchCount(name: String): Int

}