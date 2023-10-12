package com.quenhwyfar.marketapp.data.remote.datasource

import com.quenhwyfar.marketapp.data.datasource.ProductsDataSource
import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto
import com.quenhwyfar.marketapp.data.remote.service.ProductService
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor (
    private val productService : ProductService
) : ProductsDataSource {
    override suspend fun fetchProducts(): List<ProductsDto> {
        return productService.fetchProducts()
    }
}