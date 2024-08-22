package com.zcgapp.ui.repository

import com.zcgapp.model.DashboardItem
import com.zcgapp.network.ApiService
import javax.inject.Inject

class DashboardRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchDashboardData(): List<DashboardItem> {
        return apiService.getDashboardData()
    }
}
