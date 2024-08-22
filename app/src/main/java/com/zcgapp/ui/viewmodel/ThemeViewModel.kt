package com.zcgapp.ui.viewmodel


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel  : ViewModel() {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    // Add a MutableStateFlow for the icon
    private val _themeIcon = MutableStateFlow<ImageVector>(Icons.Default.FavoriteBorder)
    val themeIcon: StateFlow<ImageVector> = _themeIcon

    fun toggleTheme() {
        viewModelScope.launch {
            _isDarkTheme.value = !_isDarkTheme.value

            // Change the icon based on the current theme
            _themeIcon.value = if (_isDarkTheme.value) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            }
        }
    }
}