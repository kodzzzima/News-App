package com.example.latestnews.domain.news

/**
 * to display in list of News
 */
data class ItemNews(
    val id: Int,
    val newsId: String,
    val title: String,
    val description: String,
    val image: String,
)
