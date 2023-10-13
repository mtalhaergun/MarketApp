package com.quenhwyfar.marketapp.di

import android.content.Context
import androidx.room.Room
import com.quenhwyfar.marketapp.data.local.MarketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app : Context
    ) = Room.databaseBuilder(
        app,
        MarketDatabase::class.java,
        "MarketDatabase"
    ).build()

    @Singleton
    @Provides
    fun provideProductsDao(database : MarketDatabase) = database.productsDao()

}