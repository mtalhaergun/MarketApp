package com.quenhwyfar.marketapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "currency")
    val currency: String? = null,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String? = null,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "price")
    val price: Double? = null,
    @ColumnInfo(name = "stock")
    val stock: Int? = null,
    @ColumnInfo(name = "count")
    val count: Int? = null
)