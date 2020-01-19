package com.example.listing.framework.di

import com.example.listing.core.data.*
import com.example.listing.framework.CurrenciesDatasource
import com.example.listing.framework.UserDataSource
import com.example.listing.framework.crypto.CryptographyDatasource
import dagger.Module
import dagger.Provides

@Module
object RepositoriesModule {

    @Provides
    fun provideCurrencyRepo(datasource: CurrenciesDatasource): CurrencyRepository {
        return CurrencyRepository(datasource)
    }

    @Provides
    fun provideCryptoRepo(dataSource: CryptographyDatasource): CryptoRepository {
        return CryptoRepository(dataSource)
    }

    @Provides
    fun provideLoginRepository(dataSource: UserDataSource): LoginRepository {
        return LoginRepository(dataSource)
    }

}