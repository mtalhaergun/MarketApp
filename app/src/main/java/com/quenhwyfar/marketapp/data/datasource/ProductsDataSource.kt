package com.quenhwyfar.marketapp.data.datasource

import androidx.lifecycle.LiveData
import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity
import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto
import kotlinx.coroutines.flow.Flow

interface ProductsDataSource {

    interface Remote{
        suspend fun fetchProducts() : List<ProductsDto>
    }

    interface Local{
        fun getProducts() : Flow<List<ProductsEntity>>
        suspend fun addToCart(product : ProductsEntity)
        suspend fun delete()
        suspend fun update(product: ProductsEntity?)
        suspend fun getTotalCount() : Int
    }
}