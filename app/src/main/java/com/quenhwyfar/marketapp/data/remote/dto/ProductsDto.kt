package com.quenhwyfar.marketapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsDto(
    @Json(name = "currency")
    val currency: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "stock")
    val stock: Int
)