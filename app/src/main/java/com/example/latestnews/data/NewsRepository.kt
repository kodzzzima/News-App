package com.example.latestnews.data

import android.util.Log
import com.example.latestnews.data.local.NewsLocalDataSource
import com.example.latestnews.data.remote.NewsRemoteDataSource
import com.example.latestnews.domain.news.ItemNews
import com.example.latestnews.domain.news.News
import com.example.latestnews.domain.news.toItemNews
import com.example.latestnews.domain.news.toNewsModel
import com.example.latestnews.util.Result
import com.example.latestnews.util.doOnSuccess
import com.example.latestnews.util.mapError
import com.example.latestnews.util.mapSuccess
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.lang.NumberFormatException
import javax.inject.Inject

interface NewsRepository {

    suspend fun searchLastNews(): Result<List<ItemNews>, Throwable>

    suspend fun getNewsDetails(id: String): Result<News, Throwable>
}

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun searchLastNews(): Result<List<ItemNews>, Throwable> =
        remoteDataSource.getNews()
            .mapSuccess { dtos -> dtos.map { dto -> dto.toEntity() } }
            .doOnSuccess { newsEntities -> localDataSource.insertAll(newsEntities) }
            .mapSuccess { entries -> entries.map { newsEntity -> newsEntity.toItemNews() } }

    override suspend fun getNewsDetails(id: String): Result<News, Throwable> {
        val cashedNews = localDataSource.getById(id)
        return if (cashedNews != null) {
            val entityResult = Result.Success(cashedNews)
            entityResult.mapSuccess { entity -> entity.toNewsModel() }
        } else {
            val entityResult = Result.Error(cashedNews)
            entityResult.mapError { NullPointerException() }
        }
    }
}