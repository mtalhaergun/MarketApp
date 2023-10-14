package com.quenhwyfar.marketapp.domain.usecase

import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import javax.inject.Inject

class GetTotalCountUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke() : Int {
        return productsRepository.getTotalCount()
    }
}