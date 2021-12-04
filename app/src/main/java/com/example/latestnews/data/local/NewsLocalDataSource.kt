package com.example.latestnews.data.local

import javax.inject.Inject


interface NewsLocalDataSource {
    suspend fun getById(id: String): NewsEntity?
    suspend fun insertAll(newsEntityList: List<NewsEntity>)
    suspend fun deleteAll()
}

class NewsLocalDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao,
) : NewsLocalDataSource {
    override suspend fun getById(id: String): NewsEntity? = newsDao.getById(id)

    override suspend fun insertAll(newsEntityList: List<NewsEntity>) {
        newsDao.insertAll(newsEntityList)
    }

    override suspend fun deleteAll() {
        newsDao.deleteAll()
    }
}