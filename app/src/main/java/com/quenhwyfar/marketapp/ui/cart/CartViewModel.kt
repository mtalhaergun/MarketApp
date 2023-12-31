package com.quenhwyfar.marketapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quenhwyfar.marketapp.core.NetworkResult
import com.quenhwyfar.marketapp.data.local.dao.ProductsDao
import com.quenhwyfar.marketapp.data.remote.dto.OrderResponse
import com.quenhwyfar.marketapp.data.remote.dto.PostList
import com.quenhwyfar.marketapp.data.remote.dto.PostProducts
import com.quenhwyfar.marketapp.domain.usecase.DeleteAllProductsUseCase
import com.quenhwyfar.marketapp.domain.usecase.GetDatabaseProductsUseCase
import com.quenhwyfar.marketapp.domain.usecase.PostProductsUseCase
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
    private val deleteAllProductsUseCase: DeleteAllProductsUseCase,
    private val postProductsUseCase: PostProductsUseCase,
    private val productsDao: ProductsDao
) : ViewModel() {

    private val _productsUiState = MutableStateFlow(CartUiState())
    val productsUiState : StateFlow<CartUiState> = _productsUiState.asStateFlow()

    private val _resultLiveData = MutableLiveData<NetworkResult<OrderResponse>>()
    val resultLiveData: LiveData<NetworkResult<OrderResponse>> get() = _resultLiveData

    fun getProducts() = viewModelScope.launch {
        getDatabaseProductsUseCase.invoke().collect() {response ->
            when(response){
                is NetworkResult.Success -> _productsUiState.update { it.copy(products = response.data) }
                is NetworkResult.Loading -> _productsUiState.update { it.copy(loading = true) }
                is NetworkResult.Error -> _productsUiState.update { it.copy(error = response.message) }
            }
        }
    }

    fun deleteAllProducts() = viewModelScope.launch {
        deleteAllProductsUseCase.deleteAll()
    }

    fun postProducts(postProducts: PostList) = viewModelScope.launch {
        val result = postProductsUseCase.postData(postProducts)
        _resultLiveData.postValue(result)
    }

    fun getProductsDao() : ProductsDao{
        return productsDao
    }
}