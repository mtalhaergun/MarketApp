package com.quenhwyfar.marketapp.data.datasource

import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto

interface ProductsDataSource {

    suspend fun fetchProducts() : List<ProductsDto>

}