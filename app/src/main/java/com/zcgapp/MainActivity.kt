package com.zcgapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.zcgapp.ui.navigation.AppNavigation
import com.zcgapp.ui.screen.dashboard.ExitDialog
import com.zcgapp.ui.theme.ZCGAppTheme
import com.zcgapp.ui.viewmodel.ThemeViewModel
import com.zcgapp.utils.isInternetAvailable
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Lock orientation to portrait
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        enableEdgeToEdge()
        setContent {

            val showExitDialog = remember { mutableStateOf(false) }
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            ZCGAppTheme(darkTheme = isDarkTheme) {

                AppNavigation()
            }

            if (showExitDialog.value) {
                ExitDialog(onDismiss = { showExitDialog.value = false }, onConfirm = { finish() })
            }

            BackHandler {
                showExitDialog.value = true
            }

        }
    }
}

