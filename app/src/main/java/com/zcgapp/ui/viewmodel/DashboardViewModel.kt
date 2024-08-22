package com.zcgapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zcgapp.model.DashboardItem
import com.zcgapp.ui.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject




@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _dashboardItems = MutableStateFlow<List<DashboardItem>>(emptyList())
    val dashboardItems: StateFlow<List<DashboardItem>> = _dashboardItems

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadDashboardItems()
    }

    fun loadDashboardItems() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val data = repository.fetchDashboardData()
                _dashboardItems.value = data
                _errorMessage.value = null // Clear previous error if successful
            } catch (e: UnknownHostException) {
                _errorMessage.value = "No internet connection. Please check your connection and try again."
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.localizedMessage}"
            }finally {
                _loading.value = false
            }
        }
    }
}