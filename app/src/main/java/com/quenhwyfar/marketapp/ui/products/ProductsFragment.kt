package com.quenhwyfar.marketapp.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.quenhwyfar.marketapp.R
import com.quenhwyfar.marketapp.databinding.FragmentProductsBinding
import com.quenhwyfar.marketapp.domain.uimodel.Products
import com.quenhwyfar.marketapp.ui.products.adapter.ProductAdapter
import com.quenhwyfar.marketapp.ui.products.adapter.ProductsClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding : FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ProductsViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        collectProducts()
    }

    private fun setupRecyclerView(){
        productAdapter = ProductAdapter(object : ProductsClickListener{
            override fun onPlusClick() {

            }

            override fun onMinusClick() {

            }

        })
        binding.rvProducts.adapter = productAdapter
        productAdapter.getDao(viewModel.getProductsDao())
    }

    private fun collectProducts() = with(binding){
        viewModel.getProducts()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productsUiState.collect{uiState ->
                uiState.products?.let {products ->
                    productAdapter.products = products
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}