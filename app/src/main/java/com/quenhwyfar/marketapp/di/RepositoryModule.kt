package com.quenhwyfar.marketapp.di

import com.quenhwyfar.marketapp.data.repository.ProductsRepositoryImpl
import com.quenhwyfar.marketapp.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {
    @Binds
    abstract fun bindProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository
}