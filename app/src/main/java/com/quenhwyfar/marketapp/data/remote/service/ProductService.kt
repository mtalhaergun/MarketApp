package com.quenhwyfar.marketapp.data.remote.service

import com.quenhwyfar.marketapp.data.remote.dto.OrderResponse
import com.quenhwyfar.marketapp.data.remote.dto.PostList
import com.quenhwyfar.marketapp.data.remote.dto.PostProducts
import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductService {
    @GET("list")
    suspend fun fetchProducts() : List<ProductsDto>

    @POST("checkout")
    suspend fun postProducts(@Body PostProducts : PostList) : OrderResponse
}