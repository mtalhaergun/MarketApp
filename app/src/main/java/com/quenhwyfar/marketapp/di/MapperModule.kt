package com.quenhwyfar.marketapp.di

import com.quenhwyfar.marketapp.domain.mapper.ProductsDtoMapper
import com.quenhwyfar.marketapp.domain.mapper.ProductsEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object MapperModule {

    @Singleton
    @Provides
    fun provideProductsDtoMapper() : ProductsDtoMapper = ProductsDtoMapper()

    @Singleton
    @Provides
    fun provideProductsEntityMapper() : ProductsEntityMapper = ProductsEntityMapper()

}