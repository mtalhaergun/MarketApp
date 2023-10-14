package com.quenhwyfar.marketapp.data.remote.datasource

import com.quenhwyfar.marketapp.data.datasource.ProductsDataSource
import com.quenhwyfar.marketapp.data.remote.dto.OrderResponse
import com.quenhwyfar.marketapp.data.remote.dto.PostList
import com.quenhwyfar.marketapp.data.remote.dto.PostProducts
import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto
import com.quenhwyfar.marketapp.data.remote.service.ProductService
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor (
    private val productService : ProductService
) : ProductsDataSource.Remote {
    override suspend fun fetchProducts(): List<ProductsDto> {
        return productService.fetchProducts()
    }

    override suspend fun postProducts(PostProducts : PostList): OrderResponse {
        return productService.postProducts(PostProducts)
    }
}