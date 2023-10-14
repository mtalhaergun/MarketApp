package com.quenhwyfar.marketapp.domain.usecase

import com.quenhwyfar.marketapp.core.NetworkResult
import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDatabaseProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke() = flow {
        try {
            emit(NetworkResult.Loading())
            val products = productsRepository.getLocalProducts()
            emit(NetworkResult.Success(products))
        }catch (e : HttpException){
            emit(NetworkResult.Error(e.message()))
        }catch (e : IOException){
            emit(NetworkResult.Error(e.message))
        }
    }
}