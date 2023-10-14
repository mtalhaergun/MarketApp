package com.quenhwyfar.marketapp.domain.usecase

import com.quenhwyfar.marketapp.core.NetworkResult
import com.quenhwyfar.marketapp.data.remote.dto.OrderResponse
import com.quenhwyfar.marketapp.data.remote.dto.PostList
import com.quenhwyfar.marketapp.data.remote.dto.PostProducts
import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import javax.inject.Inject

class PostProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend fun postData(post: PostList): NetworkResult<OrderResponse> {
        return try {
            val response = productsRepository.postProducts(post)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e.message)
        }
    }
}