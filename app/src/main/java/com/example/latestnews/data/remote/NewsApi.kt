package com.example.latestnews.data.remote

import com.example.latestnews.data.remote.models.LatestNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("latest-news")
    suspend fun getNews(
        @Query("apiKey") apiKey: String
    ): LatestNewsResponse
}