package com.quenhwyfar.marketapp.domain.usecase

import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import javax.inject.Inject

class DeleteAllProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend fun deleteAll(){
        productsRepository.deleteAll()
    }
}