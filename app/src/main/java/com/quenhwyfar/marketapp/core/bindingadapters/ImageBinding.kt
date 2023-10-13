package com.quenhwyfar.marketapp.core.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:loadImage")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view).load(url)
        .centerCrop().into(view)
}