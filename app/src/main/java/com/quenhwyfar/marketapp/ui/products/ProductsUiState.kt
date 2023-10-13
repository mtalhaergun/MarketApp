package com.quenhwyfar.marketapp.ui.products

import com.quenhwyfar.marketapp.domain.uimodel.Products

data class ProductsUiState(
    val products : List<Products>? = null,
    val loading : Boolean = false,
    val error : String? = null
)