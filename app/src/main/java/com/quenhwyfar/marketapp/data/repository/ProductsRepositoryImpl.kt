package com.quenhwyfar.marketapp.data.repository

import com.quenhwyfar.marketapp.data.datasource.ProductsDataSource
import com.quenhwyfar.marketapp.domain.mapper.ProductsDtoMapper
import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import com.quenhwyfar.marketapp.domain.uimodel.Products
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor (
    private val remote : ProductsDataSource,
    private val productsDtoMapper : ProductsDtoMapper
) : ProductsRepository {
    override suspend fun getProducts(): List<Products> {
        val productsList = remote.fetchProducts()
        return productsDtoMapper.toDomainList(productsList)
    }
}