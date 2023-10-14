package com.quenhwyfar.marketapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostList(
    @Json(name = "products")
    val products : List<PostProducts>
)
