package com.learn.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.learn.cryptoapp.model.Crypto
import com.learn.cryptoapp.model.CryptoList
import com.learn.cryptoapp.repository.CryptoRepository
import com.learn.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository : CryptoRepository
) : ViewModel() {

suspend fun getCrypto(id : String) : Resource<Crypto> {
    return repository.getCrypto(id)
}

}