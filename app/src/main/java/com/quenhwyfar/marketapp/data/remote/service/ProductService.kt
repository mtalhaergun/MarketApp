package com.quenhwyfar.marketapp.data.remote.service

import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto
import retrofit2.http.GET

interface ProductService {
    @GET("list")
    suspend fun fetchProducts() : List<ProductsDto>
}