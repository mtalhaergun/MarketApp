package com.quenhwyfar.marketapp.data.repository

import com.quenhwyfar.marketapp.data.datasource.ProductsDataSource
import com.quenhwyfar.marketapp.domain.mapper.ProductsDtoMapper
import com.quenhwyfar.marketapp.domain.mapper.ProductsEntityMapper
import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import com.quenhwyfar.marketapp.domain.uimodel.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor (
    private val remote : ProductsDataSource.Remote,
    private val local : ProductsDataSource.Local,
    private val productsEntityMapper: ProductsEntityMapper,
    private val productsDtoMapper : ProductsDtoMapper
) : ProductsRepository {
    override suspend fun getProducts(): List<Products> {
        val productsList = remote.fetchProducts()
        return productsDtoMapper.toDomainList(productsList)
    }

    override fun getLocalProducts(): Flow<List<Products>> {
        return local.getProducts().map {
            productsEntityMapper.toDomainList(it)
        }
    }

    override suspend fun addToCart(products: Products) {
        local.addToCart(productsEntityMapper.fromDomain(products))
    }

    override suspend fun deleteAll() {
        local.delete()
    }

    override suspend fun updateProduct(products: Products) {
        local.update(productsEntityMapper.fromDomain(products))
    }
}