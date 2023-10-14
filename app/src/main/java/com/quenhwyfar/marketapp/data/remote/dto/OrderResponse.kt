package com.quenhwyfar.marketapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderResponse(
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "orderID")
    val orderID: String? = null
)