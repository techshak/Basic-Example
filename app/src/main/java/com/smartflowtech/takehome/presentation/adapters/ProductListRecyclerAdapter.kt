package com.smartflowtech.takehome.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smartflowtech.takehome.data.model.Product
import com.smartflowtech.takehome.databinding.SingleItemCardBinding
import com.smartflowtech.takehome.utils.Constants.Companion.IMAGE

class ProductListRecyclerAdapter:
    RecyclerView.Adapter<ProductListRecyclerAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(binding:SingleItemCardBinding): RecyclerView.ViewHolder(binding.root){
        private var productImage = binding.productImageview
        private var productId = binding.productIdTextview
        private var productSlug = binding.slugTextview
        private var category = binding.categoryTextview

        fun bindData(item:Product,image:String)= with (itemView){
            Glide.with(context).load(image).into(productImage)
            productId.text = item.id.toString()
            productSlug.text = item.slug
            category.text = item.category_name

        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = SingleItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(differ.currentList[position], IMAGE[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}