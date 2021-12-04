package com.example.latestnews.data.local

import androidx.room.*

@Dao
interface NewsDao {

    @Query(
        """
        SELECT *
        FROM ${NewsEntity.TABLE_NAME}
        WHERE ${NewsEntity.NEWS_ID}=:id
        LIMIT 1
        """
    )
    suspend fun getById(id: String): NewsEntity?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(searchedNews: List<NewsEntity>)

    @Transaction
    @Query("DELETE FROM ${NewsEntity.TABLE_NAME}")
    suspend fun deleteAll()
}