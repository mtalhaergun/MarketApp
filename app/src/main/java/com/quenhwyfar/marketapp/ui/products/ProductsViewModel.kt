package com.quenhwyfar.marketapp.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quenhwyfar.marketapp.core.NetworkResult
import com.quenhwyfar.marketapp.data.local.dao.ProductsDao
import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import com.quenhwyfar.marketapp.domain.uimodel.Products
import com.quenhwyfar.marketapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor (
    private val getProductsUseCase: GetProductsUseCase,
    private val productsRepository: ProductsRepository,
    private val productsDao: ProductsDao,
) : ViewModel() {

    private val _productsUiState = MutableStateFlow(ProductsUiState())
    val productsUiState : StateFlow<ProductsUiState> = _productsUiState.asStateFlow()

    var productCount = 0

    fun getProducts() = viewModelScope.launch {
        getProductsUseCase.invoke().collect() {response ->
            when(response){
                is NetworkResult.Success -> _productsUiState.update { it.copy(products = response.data) }
                is NetworkResult.Loading -> _productsUiState.update { it.copy(loading = true) }
                is NetworkResult.Error -> _productsUiState.update { it.copy(error = response.message) }
            }
        }
    }

    fun getProductsDao() : ProductsDao{
        return productsDao
    }
}