package com.smartflowtech.takehome.data.repository

import com.smartflowtech.takehome.network.ProductAPI
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val api: ProductAPI) {

    suspend fun getProductList() = api.getProductList()
}
