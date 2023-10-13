package com.quenhwyfar.marketapp.domain.usecase

import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import com.quenhwyfar.marketapp.domain.uimodel.Products
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend fun addProduct(product: Products): Products {
        return product.id?.let { id ->
            if (product.count == 1) {
                if (product.stock != null && product.count >= product.stock) {
                    product
                } else {
                    productsRepository.addToCart(product)
                    product.copy(count = product.count)
                }
            } else {
                if (product.stock != null && product.count > product.stock) {
                    product.copy(count = product.count - 1)
                } else {
                    productsRepository.updateProduct(product.copy(count = product.count))
                    product.copy(count = product.count)
                }
            }
        } ?: product
    }
}