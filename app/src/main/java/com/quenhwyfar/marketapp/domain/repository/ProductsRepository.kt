package com.quenhwyfar.marketapp.domain.repository

import com.quenhwyfar.marketapp.domain.uimodel.Products

interface ProductsRepository {

    suspend fun getProducts() : List<Products>

}