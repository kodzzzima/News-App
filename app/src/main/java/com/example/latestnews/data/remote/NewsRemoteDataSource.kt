package com.example.latestnews.data.remote

import com.example.latestnews.data.remote.models.LatestNewsDto
import com.example.latestnews.util.Result
import com.example.latestnews.util.runOperationCatching
import javax.inject.Inject

interface NewsRemoteDataSource {
    suspend fun getNews(): Result<List<LatestNewsDto>, Throwable>
}

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRemoteDataSource {
    override suspend fun getNews(): Result<List<LatestNewsDto>, Throwable> =
        runOperationCatching { newsApi.getNews(API_KEY).news }

    companion object {
        const val API_KEY = "0A8Cn6hMMnyMHopgL4QIGuzt1VDt8_aW-lzXeNjgR-MG8Q4l"
    }
}