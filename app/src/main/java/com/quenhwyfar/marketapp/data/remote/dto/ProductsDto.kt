package com.quenhwyfar.marketapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsDto(
    @Json(name = "currency")
    val currency: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "price")
    val price: Double? = null,
    @Json(name = "stock")
    val stock: Int? = null
)