package com.example.latestnews.data

import com.example.latestnews.data.local.NewsEntity
import com.example.latestnews.data.remote.models.LatestNewsDto

fun LatestNewsDto.toEntity(): NewsEntity = NewsEntity(
    newsId = this.newsId,
    title = this.title,
    description = this.description,
    author = this.author,
    image = this.image,
    language = this.language,
    published = this.published,
    url = this.url,
)