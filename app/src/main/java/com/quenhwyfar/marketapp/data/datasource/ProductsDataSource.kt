package com.quenhwyfar.marketapp.data.datasource

import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity
import com.quenhwyfar.marketapp.data.remote.dto.OrderResponse
import com.quenhwyfar.marketapp.data.remote.dto.PostList
import com.quenhwyfar.marketapp.data.remote.dto.PostProducts
import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto
import kotlinx.coroutines.flow.Flow

interface ProductsDataSource {

    interface Remote{
        suspend fun fetchProducts() : List<ProductsDto>
        suspend fun postProducts(PostProducts : PostList) : OrderResponse
    }

    interface Local{
        fun getProducts() : Flow<List<ProductsEntity>>
        suspend fun addToCart(product : ProductsEntity)
        suspend fun delete()
        suspend fun update(product: ProductsEntity?)
        suspend fun getTotalCount() : Int
    }
}