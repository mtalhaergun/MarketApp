package com.quenhwyfar.marketapp.ui.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quenhwyfar.marketapp.databinding.ItemProductBinding
import com.quenhwyfar.marketapp.domain.uimodel.Products

class ProductAdapter(
    private val productsClickListener: ProductsClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductVH>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }

    }

    private val diffList = AsyncListDiffer(this,diffUtil)
    var products : List<Products>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    class ProductVH(
        private val binding : ItemProductBinding,
        private val productsClickListener: ProductsClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product : Products){
            binding.product = product
            binding.executePendingBindings()

            binding.buttonPlus.setOnClickListener {
                productsClickListener.onPlusClick(product,binding.buttonCount.text.toString().toInt())
            }
            binding.buttonMinus.setOnClickListener {
                productsClickListener.onMinusClick(product,binding.buttonCount.text.toString().toInt())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductVH(binding,productsClickListener)
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}