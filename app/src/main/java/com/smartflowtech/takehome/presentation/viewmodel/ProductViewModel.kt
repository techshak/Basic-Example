package com.smartflowtech.takehome.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartflowtech.takehome.data.model.ProductList
import com.smartflowtech.takehome.data.repository.ProductsRepository
import com.smartflowtech.takehome.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel@Inject constructor(private val repository: ProductsRepository) : ViewModel() {
    private var _products = MutableLiveData<Resource<ProductList>>()
    val productList: LiveData<Resource<ProductList>> get() = _products

    fun getProductList() = viewModelScope.launch(Dispatchers.IO) {
        _products.postValue(Resource.Loading())
        val onlineProducts = repository.getProductList()
        Log.d("Api data", onlineProducts.body().toString())
        _products.postValue(handleProductData(onlineProducts))
    }

    private fun handleProductData(userData: Response<ProductList>): Resource<ProductList> {
        if (userData.isSuccessful) {
            userData.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(null, userData.message())
    }
}
