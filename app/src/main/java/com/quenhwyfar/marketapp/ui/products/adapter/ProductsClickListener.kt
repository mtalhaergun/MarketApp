package com.quenhwyfar.marketapp.ui.products.adapter

import com.quenhwyfar.marketapp.domain.uimodel.Products

interface ProductsClickListener {
    fun onPlusClick(products: Products, count : Int)
    fun onMinusClick(products: Products, count: Int)
}