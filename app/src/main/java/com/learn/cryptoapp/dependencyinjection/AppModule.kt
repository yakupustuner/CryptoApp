package com.learn.cryptoapp.dependencyinjection

import com.google.gson.Gson
import com.learn.cryptoapp.repository.CryptoRepository
import com.learn.cryptoapp.service.CryptoAPI
import com.learn.cryptoapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoRepository(api : CryptoAPI) = CryptoRepository(api)

    @Singleton
    @Provides
    fun provideCryptoApi() : CryptoAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()
            .create(CryptoAPI::class.java)
    }
}