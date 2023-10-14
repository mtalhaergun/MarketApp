package com.quenhwyfar.marketapp.ui.cart

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.quenhwyfar.marketapp.core.NetworkResult
import com.quenhwyfar.marketapp.data.remote.dto.PostList
import com.quenhwyfar.marketapp.data.remote.dto.PostProducts
import com.quenhwyfar.marketapp.databinding.FragmentCartBinding
import com.quenhwyfar.marketapp.domain.uimodel.Products
import com.quenhwyfar.marketapp.ui.cart.adapter.CartAdapter
import com.quenhwyfar.marketapp.ui.cart.adapter.CartClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding : FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CartViewModel by viewModels()

    private lateinit var cartAdapter : CartAdapter

    private var toApiList : List<Products>? = null

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
        observe()
        listener()
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
                uiState.products.let {flow ->
                    flow?.collect{ list ->
                        var totalPrice = 0.0
                        cartAdapter.products = list
                        toApiList = list
                        list.forEach { product ->
                            totalPrice += product.count * product.price!!
                        }
                        if(list.isEmpty()) binding.totalPrice.text = "0"
                        else binding.totalPrice.text = list[0].currency + String.format("%.2f",totalPrice)
                    }

                }
            }
        }
    }

    private fun observe(){
        viewModel.resultLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    showPostDialog(true, result.data?.orderID)
                    viewModel.deleteAllProducts()
                }

                is NetworkResult.Error -> {
                    showPostDialog(false,id = null)
                }

                is NetworkResult.Loading -> {
                }
            }
        }
    }

    private fun listener(){
        binding.textViewSil.setOnClickListener {
            showConfirmationDialog()
        }

        binding.buyButton.setOnClickListener {
            val postProductsList = mutableListOf<PostProducts>()
            for (product in toApiList!!) {
                if (product.id != null && product.count > 0) {
                    val postProduct = PostProducts(
                        id = product.id,
                        amount = product.count
                    )
                    postProductsList.add(postProduct)
                }
            }
            viewModel.postProducts(PostList(postProductsList))
        }

        binding.textViewKapat.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Sepeti Boşalt")
        alertDialog.setMessage("Tüm ürünler sepetten silinecek, emin misiniz?")

        alertDialog.setNegativeButton("Hayır") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.setPositiveButton("Evet") { _, _ ->
            viewModel.deleteAllProducts()
        }
        alertDialog.show()
    }

    private fun showPostDialog(
        isSuccess : Boolean,
        id : String? = null,
    ) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Satın Alma İşlemi")
        if(isSuccess) {
            builder.setMessage("Başarılı!\n\nOrderID: ${id}")
        }
        else builder.setMessage("Başarısız!")

        builder.setPositiveButton("Çıkış") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}