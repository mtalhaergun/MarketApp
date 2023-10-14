package com.quenhwyfar.marketapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostProducts(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "amount")
    val amount: Int? = null
)