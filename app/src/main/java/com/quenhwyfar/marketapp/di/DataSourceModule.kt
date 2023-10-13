package com.quenhwyfar.marketapp.di

import com.quenhwyfar.marketapp.data.datasource.ProductsDataSource
import com.quenhwyfar.marketapp.data.local.datasource.ProductsLocalDataSource
import com.quenhwyfar.marketapp.data.remote.datasource.ProductsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {

    @Binds
    abstract fun bindProductsRemoteDataSource(productsRemoteDataSource: ProductsRemoteDataSource): ProductsDataSource.Remote

    @Binds
    abstract fun bindProductsLocalDataSource(productsLocalDataSource : ProductsLocalDataSource): ProductsDataSource.Local
}