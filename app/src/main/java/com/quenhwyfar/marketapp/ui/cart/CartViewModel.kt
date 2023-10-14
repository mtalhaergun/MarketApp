package com.quenhwyfar.marketapp.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quenhwyfar.marketapp.core.NetworkResult
import com.quenhwyfar.marketapp.data.local.dao.ProductsDao
import com.quenhwyfar.marketapp.domain.usecase.GetDatabaseProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getDatabaseProductsUseCase: GetDatabaseProductsUseCase,
    private val productsDao: ProductsDao
) : ViewModel() {

    private val _productsUiState = MutableStateFlow(CartUiState())
    val productsUiState : StateFlow<CartUiState> = _productsUiState.asStateFlow()

    fun getProducts() = viewModelScope.launch {
        getDatabaseProductsUseCase.invoke().collect() {response ->
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