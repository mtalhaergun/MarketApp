package com.quenhwyfar.marketapp.domain.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(
    val currency: String? = null,
    val id: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val price: Double? = null,
    val stock: Int? = null,
    val count: Int = 0
) : Parcelable