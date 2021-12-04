package com.example.latestnews.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NewsEntity.TABLE_NAME)
data class NewsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Int = 0,

    @ColumnInfo(name = NEWS_ID)
    var newsId: String,

    @ColumnInfo(name = TITLE)
    var title: String,

    @ColumnInfo(name = DESCRIPTION)
    var description: String,

    @ColumnInfo(name = AUTHOR)
    var author: String,

    @ColumnInfo(name = IMAGE)
    var image: String,

    @ColumnInfo(name = LANGUAGE)
    var language: String,

    @ColumnInfo(name = PUBLISHED)
    var published: String,

    @ColumnInfo(name = URL)
    var url: String,
) {
    companion object {

        const val TABLE_NAME = "news"

        const val NEWS_ID = "news_id"
        const val AUTHOR = "author"
        const val TITLE = "title"
        const val IMAGE = "image"
        const val DESCRIPTION = "description"
        const val LANGUAGE = "language"
        const val PUBLISHED = "published"
        const val URL = "url"
        const val ID = "id"
    }
}
