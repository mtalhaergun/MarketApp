package com.quenhwyfar.marketapp.domain.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(
    val currency: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Double,
    val stock: Int
) : Parcelable