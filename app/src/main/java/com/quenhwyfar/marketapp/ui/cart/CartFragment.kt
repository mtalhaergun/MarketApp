package com.quenhwyfar.marketapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.quenhwyfar.marketapp.R
import com.quenhwyfar.marketapp.databinding.FragmentCartBinding
import com.quenhwyfar.marketapp.ui.cart.adapter.CartAdapter
import com.quenhwyfar.marketapp.ui.cart.adapter.CartClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding : FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CartViewModel by viewModels()

    private lateinit var cartAdapter : CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        collectProducts()
    }

    private fun setupRecyclerView(){
        cartAdapter = CartAdapter(object : CartClickListener{
            override fun onPlusClick() {

            }

            override fun onMinusClick() {

            }

        })
        binding.rvProducts.adapter = cartAdapter
        cartAdapter.getDao(viewModel.getProductsDao())
    }

    private fun collectProducts() = with(binding){
        viewModel.getProducts()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productsUiState.collect{uiState ->
                uiState.products?.let {flow ->
                    flow.collect{
                        cartAdapter.products = it
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}