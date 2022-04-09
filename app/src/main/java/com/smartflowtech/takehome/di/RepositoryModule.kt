package com.smartflowtech.takehome.di

import com.smartflowtech.takehome.data.repository.ProductsRepository
import com.smartflowtech.takehome.network.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideProductsRepository(
        api: ProductAPI
    ): ProductsRepository {
        return ProductsRepository(api)
    }
}
