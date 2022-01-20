package com.learn.cryptoapp.repository

import com.learn.cryptoapp.model.Crypto
import com.learn.cryptoapp.model.CryptoList
import com.learn.cryptoapp.service.CryptoAPI
import com.learn.cryptoapp.util.Constants.API_KEY
import com.learn.cryptoapp.util.Constants.CALL_ATTRIBUTES
import com.learn.cryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository@Inject constructor(
    private val api: CryptoAPI
) {

    suspend fun getCryptoList() : Resource<CryptoList> {
        val response = try {
            api.getCryptoList(API_KEY)

        }catch (e: Exception) {
            return Resource.Error("Error.")

        }

        return Resource.Success(response)
    }

    suspend fun getCrypto(id : String) : Resource<Crypto> {
        val response = try {
            api.getCrypto(API_KEY,id, CALL_ATTRIBUTES)


        }catch (e: Exception) {
            return Resource.Error("Error.")

        }

        return Resource.Success(response)
    }
}