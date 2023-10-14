package com.quenhwyfar.marketapp.ui.products

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
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
        observe()
        listeners()
    }

    private fun setupRecyclerView(){
        productAdapter = ProductAdapter(object : ProductsClickListener{
            override fun onPlusClick() {
                viewModel.updateTotalCount()
            }

            override fun onMinusClick() {
                viewModel.updateTotalCount()
            }

        })
        binding.rvProducts.adapter = productAdapter
        binding.rvProducts.addItemDecoration(GridSpacingItemDecoration(3, spacing = 24))
        productAdapter.getDao(viewModel.getProductsDao())
    }

    private fun collectProducts() = with(binding){
        viewModel.getProducts()
        viewModel.updateTotalCount()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productsUiState.collect{uiState ->
                uiState.products?.let {products ->
                    productAdapter.products = products
                }
            }
        }
    }

    private fun observe(){
        viewModel.totalCount.observe(viewLifecycleOwner, Observer {
            binding.productCount.text = it.toString()
        })
    }

    private fun listeners(){
        binding.cart.setOnClickListener {
            val navigation = ProductsFragmentDirections.actionProductsFragment2ToCartFragment()
            Navigation.findNavController(it).navigate(navigation)
        }
    }

    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}