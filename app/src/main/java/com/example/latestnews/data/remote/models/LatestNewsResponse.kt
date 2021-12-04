package com.example.latestnews.data.remote.models

data class LatestNewsResponse(
    val news: List<LatestNewsDto>,
    val page: Int,
    val status: String
)