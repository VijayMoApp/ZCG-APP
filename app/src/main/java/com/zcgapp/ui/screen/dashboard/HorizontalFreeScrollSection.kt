package com.zcgapp.ui.screen.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zcgapp.R
import com.zcgapp.model.Item

@Composable
fun HorizontalFreeScrollSection(items: List<Item>) {


    val products = stringResource(id = R.string.products)

    Text(
        text = products,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(16.dp)
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            ProductItem(item = item)
        }
    }
}

@Composable
fun ProductItem(item: Item) {
    Card(
        modifier = Modifier
            .width(140.dp)  // Set the width of the card
            .padding(4.dp)
            .clickable { /* Handle click */ },
        shape = RoundedCornerShape(12.dp),  // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),  // Shadow for the card
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,  // Background color
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = item.title,
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
        }
    }
}
