package com.zcgapp.model

data class DashboardItem(
    val sectionType: String,
    val items: List<Item>
)

data class Item(
    val title: String,
    val image: String
)
