package com.zcgapp.ui.screen.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.zcgapp.R
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://zapcom.ai/wp-content/uploads/2023/09/Zapcom-Logo-300x60.png"), // Replace with your image URL
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop
        )
    }

    LaunchedEffect(Unit) {
        delay(3000) // Show splash screen for 3 seconds
        navController.navigate("dashboard") {
            popUpTo("splash") { inclusive = true } // Remove splash screen from the back stack
        }
    }
}