package com.smartflowtech.takehome.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.smartflowtech.takehome.R
import com.smartflowtech.takehome.databinding.ActivityMainBinding
import com.smartflowtech.takehome.presentation.adapters.ProductListRecyclerAdapter
import com.smartflowtech.takehome.presentation.viewmodel.ProductViewModel
import com.smartflowtech.takehome.utils.Resource
import com.smartflowtech.takehome.utils.connectivity.ConnectivityLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private val viewModel: ProductViewModel by viewModels()
    private var productListRecyclerAdapter = ProductListRecyclerAdapter()
    private lateinit var networkSnackbar :Snackbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Takehome)
        connectivityLiveData = ConnectivityLiveData(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.productRecyclerview.adapter = productListRecyclerAdapter
        networkSnackbar =Snackbar.make(
            binding.productScreenLayout,
            R.string.no_network,
            Snackbar.LENGTH_INDEFINITE
        )

        connectivityLiveData.observe(this, { networkState ->
            if (networkState == true) {
                // Toggle the visibility of the network error layer
                viewModel.getProductList()
                networkSnackbar.dismiss()
            }else {networkSnackbar.show() }
        })



        viewModel.productList.observe(
            this, { products->
                when(products) {
                    is Resource.Loading->{
                        binding.productProgressbar.visibility= View.VISIBLE
                        }
                    is Resource.Error->{
                        Toast.makeText(this, "Error:" , Toast.LENGTH_LONG).show()
                        binding.productProgressbar.visibility= View.GONE
                        }
                    is Resource.Success->{
                        binding.productProgressbar.visibility= View.GONE
                        products.data?.let {
                            productListRecyclerAdapter.differ.submitList(it.data)
                        }
                     }
                }

            }
        )
    }

}