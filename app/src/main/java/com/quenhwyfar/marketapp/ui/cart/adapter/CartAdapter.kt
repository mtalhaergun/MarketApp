package com.quenhwyfar.marketapp.ui.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.quenhwyfar.marketapp.data.local.dao.ProductsDao
import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity
import com.quenhwyfar.marketapp.databinding.ItemCartProductBinding
import com.quenhwyfar.marketapp.domain.uimodel.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    var productsDao : ProductsDao? = null
    class CartVH(
        private val binding : ItemCartProductBinding,
        private val cartClickListener: CartClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product : Products,productsDao: ProductsDao){
            binding.product = product


            val job = CoroutineScope(Dispatchers.Main).launch {
                if(productsDao.searchName(product.name!!)){
                    val productCount = productsDao.searchCount(product.name!!)
                    binding.buttonCount.visibility = View.VISIBLE
                    binding.buttonMinus.visibility = View.VISIBLE
                    binding.buttonCount.text = productCount.toString()
                    binding.productPrice.text = product.currency + (productCount * product.price!!).toString()
                }else{
                    binding.buttonCount.visibility = View.GONE
                    binding.buttonMinus.visibility = View.GONE
                }
            }

            binding.buttonPlus.setOnClickListener {
                val job = CoroutineScope(Dispatchers.Main).launch {
                    if(productsDao.searchName(product.name!!)){
                        val productCount = productsDao.searchCount(product.name!!)
                        if(productCount != product.stock){
                            productsDao.update(
                                ProductsEntity(
                                    id = product.id!!,
                                    currency = product.currency,
                                    imageUrl = product.imageUrl,
                                    name = product.name,
                                    price = product.price,
                                    stock = product.stock,
                                    count = productCount+1
                                )
                            )
                            cartClickListener.onPlusClick()
                            binding.buttonCount.visibility = View.VISIBLE
                            binding.buttonMinus.visibility = View.VISIBLE
                            binding.buttonCount.text = (productCount+1).toString()
                            binding.productPrice.text = product.currency + (productCount * product.price!!).toString()
                        }
                    }else{
                        productsDao.insert(
                            ProductsEntity(
                                id = product.id!!,
                                currency = product.currency,
                                imageUrl = product.imageUrl,
                                name = product.name,
                                price = product.price,
                                stock = product.stock,
                                count = product.count+1
                            )
                        )
                        cartClickListener.onPlusClick()
                        binding.buttonCount.visibility = View.VISIBLE
                        binding.buttonMinus.visibility = View.VISIBLE
                        binding.buttonCount.text = "1"
                        binding.productPrice.text = product.currency + product.price.toString()
                    }
                }

            }
            binding.buttonMinus.setOnClickListener {
                val job = CoroutineScope(Dispatchers.Main).launch {
                    if(productsDao.searchName(product.name!!)){
                        val productCount = productsDao.searchCount(product.name!!)
                        if(productCount > 1){
                            productsDao.update(
                                ProductsEntity(
                                    id = product.id!!,
                                    currency = product.currency,
                                    imageUrl = product.imageUrl,
                                    name = product.name,
                                    price = product.price,
                                    stock = product.stock,
                                    count = productCount-1
                                )
                            )
                            cartClickListener.onMinusClick()
                            binding.buttonCount.visibility = View.VISIBLE
                            binding.buttonMinus.visibility = View.VISIBLE
                            binding.buttonCount.text = (productCount-1).toString()
                            binding.productPrice.text = product.currency + (productCount * product.price!!).toString()
                        }else{
                            productsDao.delete(
                                ProductsEntity(
                                    id = product.id!!,
                                    currency = product.currency,
                                    imageUrl = product.imageUrl,
                                    name = product.name,
                                    price = product.price,
                                    stock = product.stock,
                                    count = productCount-1
                                )
                            )
                            cartClickListener.onMinusClick()
                            binding.buttonCount.visibility = View.GONE
                            binding.buttonMinus.visibility = View.GONE
                            binding.buttonCount.text = (productCount-1).toString()
                            binding.productPrice.text = product.currency + product.price.toString()
                        }
                    }
                }
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        val binding = ItemCartProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartVH(binding,cartClickListener)
    }

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        holder.bind(products[position],productsDao!!)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun getDao(pdao: ProductsDao){
        productsDao = pdao
    }
}