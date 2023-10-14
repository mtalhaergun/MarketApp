package com.quenhwyfar.marketapp.data.local.datasource

import com.quenhwyfar.marketapp.data.datasource.ProductsDataSource
import com.quenhwyfar.marketapp.data.local.dao.ProductsDao
import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsLocalDataSource @Inject constructor (
    private val productsDao: ProductsDao
) : ProductsDataSource.Local {
    override fun getProducts(): Flow<List<ProductsEntity>> {
        return productsDao.getProducts()
    }

    override suspend fun addToCart(product: ProductsEntity) {
        productsDao.insert(product)
    }

    override suspend fun delete() {
        productsDao.deleteAll()
    }

    override suspend fun update(product: ProductsEntity?) {
        productsDao.update(product)
    }

    override suspend fun getTotalCount(): Int {
        return productsDao.getTotalCount()
    }
}