package com.quenhwyfar.marketapp.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.quenhwyfar.marketapp.core.NetworkResult
import com.quenhwyfar.marketapp.data.local.dao.ProductsDao
import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import com.quenhwyfar.marketapp.domain.uimodel.Products
import com.quenhwyfar.marketapp.domain.usecase.GetProductsUseCase
import com.quenhwyfar.marketapp.domain.usecase.GetTotalCountUseCase
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
    private val getTotalCountUseCase: GetTotalCountUseCase,
    private val productsRepository: ProductsRepository,
    private val productsDao: ProductsDao,
) : ViewModel() {

    private val _productsUiState = MutableStateFlow(ProductsUiState())
    val productsUiState : StateFlow<ProductsUiState> = _productsUiState.asStateFlow()

    private val _totalCount = MutableLiveData<Int?>()
    val totalCount: LiveData<Int?>
        get() = _totalCount

    fun getProducts() = viewModelScope.launch {
        getProductsUseCase.invoke().collect() {response ->
            when(response){
                is NetworkResult.Success -> _productsUiState.update { it.copy(products = response.data) }
                is NetworkResult.Loading -> _productsUiState.update { it.copy(loading = true) }
                is NetworkResult.Error -> _productsUiState.update { it.copy(error = response.message) }
            }
        }
    }

    fun updateTotalCount() = viewModelScope.launch {
        val result = getTotalCountUseCase()
        if (result != null) {
            _totalCount.value = result
        } else {
            _totalCount.value = 0
        }
    }

    fun getProductsDao() : ProductsDao{
        return productsDao
    }
}