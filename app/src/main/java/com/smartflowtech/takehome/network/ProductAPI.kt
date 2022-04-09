package com.smartflowtech.takehome.network

import com.smartflowtech.takehome.data.model.ProductList
import com.smartflowtech.takehome.utils.Constants.Companion.PRODUCT_LIST
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET(PRODUCT_LIST)
    suspend fun getProductList(): Response<ProductList>
}