package com.quenhwyfar.marketapp.di

import com.quenhwyfar.marketapp.domain.mapper.ProductsDtoMapper
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

}