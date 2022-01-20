package com.learn.cryptoapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.learn.cryptoapp.model.Crypto
import com.learn.cryptoapp.model.CryptoItem
import com.learn.cryptoapp.util.Resource
import com.learn.cryptoapp.viewmodel.CryptoDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch

@Composable
fun CryptoDetailScreen(
    id:String,
    price:String,
    navController: NavController,
viewModel : CryptoDetailViewModel = hiltViewModel()
) {

    var cryptoItem by remember {
        mutableStateOf<Resource<Crypto>>(Resource.Loading())
    }

    LaunchedEffect(key1 = Unit) {
        cryptoItem = viewModel.getCrypto(id)
        println(cryptoItem.data)

    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(cryptoItem) {

                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!![0]
                    Text(text = selectedCrypto.name,
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center
                    )

                    Image(painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Text(text = price,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center

                    )

                }

                is Resource.Error -> {
                    Text(cryptoItem.message!!)
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }

            }

        }
    }
}
