package com.quenhwyfar.marketapp.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quenhwyfar.marketapp.databinding.ItemCartProductBinding
import com.quenhwyfar.marketapp.domain.uimodel.Products

class CartAdapter(
    private val cartClickListener: CartClickListener
) : RecyclerView.Adapter<CartAdapter.CartVH>() {

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

    class CartVH(
        private val binding : ItemCartProductBinding,
        private val cartClickListener: CartClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product : Products){
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        val binding = ItemCartProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartVH(binding,cartClickListener)
    }

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}