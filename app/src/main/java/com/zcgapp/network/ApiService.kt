package com.zcgapp.network

import com.zcgapp.model.DashboardItem
import com.zcgapp.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

interface ApiService {
    @GET(AppConstants.DASHBOARD_URL)
    suspend fun getDashboardData(): List<DashboardItem>
}
