package com.quenhwyfar.marketapp.domain.repository

import com.quenhwyfar.marketapp.data.remote.dto.OrderResponse
import com.quenhwyfar.marketapp.data.remote.dto.PostList
import com.quenhwyfar.marketapp.data.remote.dto.PostProducts
import com.quenhwyfar.marketapp.domain.uimodel.Products
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts() : List<Products>

    fun getLocalProducts() : Flow<List<Products>>
    suspend fun addToCart(products: Products)
    suspend fun deleteAll()
    suspend fun updateProduct(products : Products)
    suspend fun getTotalCount() : Int
    suspend fun postProducts(PostProducts : PostList) : OrderResponse

}