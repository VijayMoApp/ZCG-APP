package com.zcgapp.ui.screen.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zcgapp.R
@Composable
fun BannerSection(imageUrl: String) {


    val promotion = stringResource(id = R.string.promotion)

    Text(
        text = promotion,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(16.dp)
    )
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)  // Set height to 240dp
            .padding(16.dp), // Add padding around the image
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(10.dp))
}
