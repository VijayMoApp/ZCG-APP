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
import coil.compose.rememberImagePainter
import com.zcgapp.R

@Composable
fun SplitBannerSection(imageUrls: List<String>) {

    val gridBanner = stringResource(id = R.string.gridBanner)
    if (imageUrls.size == 2) {

        Text(
            text = gridBanner,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(240.dp)  // Set height to 240dp
        ) {
            // First image
            Image(
                painter = rememberAsyncImagePainter(model = imageUrls[0]),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)  // Equal width
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop  // Crop to fill the space
            )

            Spacer(modifier = Modifier.width(8.dp))  // Optional spacing between images

            // Second image
            Image(
                painter = rememberAsyncImagePainter(model = imageUrls[1]),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)  // Equal width
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop  // Crop to fill the space
            )
        }
    }
}
