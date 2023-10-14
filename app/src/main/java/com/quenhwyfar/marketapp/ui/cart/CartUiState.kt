package com.quenhwyfar.marketapp.ui.cart

import com.quenhwyfar.marketapp.domain.uimodel.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CartUiState(
    val products: Flow<List<Products>>? = emptyFlow(),
    val loading: Boolean? = false,
    val error: String? = null
)