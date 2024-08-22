package com.zcgapp.ui.screen.dashboard

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.zcgapp.ui.viewmodel.DashboardViewModel
import com.zcgapp.ui.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch
import android.provider.Settings
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.platform.LocalContext


import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zcgapp.MainActivity
import com.zcgapp.R

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    themeViewModel: ThemeViewModel = hiltViewModel(),

) {
    val context = LocalContext.current
    val dashboardItems by dashboardViewModel.dashboardItems.collectAsState()
    val errorMessage by dashboardViewModel.errorMessage.collectAsState()
    val isLoading by dashboardViewModel.loading.collectAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val showExitDialog = remember { mutableStateOf(false) }






    // Show Snack bar for errors
    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = "Settings",
                    duration = SnackbarDuration.Indefinite
                ).also { result ->
                    if (result == SnackbarResult.ActionPerformed) {
                        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CustomAppBar(
                profileImageUrl = "https://images.pexels.com/photos/14569231/pexels-photo-14569231.jpeg",
                userName = "Vijayakumar",
                textColor = Color.White,
                onThemeToggle = { themeViewModel.toggleTheme() }
            )
        }
    ) { padding ->

        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                // Display the progress bar in the center of the screen
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    // Display Product
                    dashboardItems.firstOrNull { it.sectionType == "horizontalFreeScroll" }?.let { section ->
                        item {
                            HorizontalFreeScrollSection(items = section.items)
                        }
                    }

                    // Display SplitBannerSection
                    dashboardItems.firstOrNull { it.sectionType == "splitBanner" }?.let { section ->
                        item {
                            SplitBannerSection(imageUrls = section.items.map { it.image })
                        }
                    }

                    // Display BannerSection
                    dashboardItems.firstOrNull { it.sectionType == "banner" }?.let { section ->
                        section.items.firstOrNull()?.let { item ->
                            item {
                                BannerSection(imageUrl = item.image)
                            }
                        }
                    }
                }
            }

            // Optional: Scroll to the top item
            LaunchedEffect(key1 = dashboardItems) {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        }
    }

    BackHandler {
        showExitDialog.value = true
    }

    if (showExitDialog.value) {
        ExitDialog(onDismiss = { showExitDialog.value = false }, onConfirm = {  (context as? MainActivity)?.finish() })
    }
}


@Composable
fun ExitDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {


    val existApp = stringResource(id = R.string.exist_app)
    val closeApp = stringResource(id = R.string.want_close_app)
    val yes = stringResource(id = R.string.Yes)
    val no = stringResource(id = R.string.no)



    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(existApp) },
        text = { Text(closeApp) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(yes)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(no)
            }
        }
    )
}