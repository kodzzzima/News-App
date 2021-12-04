package com.example.latestnews.domain.news

import com.example.latestnews.data.local.NewsEntity

fun NewsEntity.toItemNews() = ItemNews(
    id = this.id,
    newsId = this.newsId,
    title = this.title,
    description = this.description,
    image = this.image
)

fun NewsEntity.toNewsModel() = News(
    id = this.id,
    newsId = this.newsId,
    title = this.title,
    description = this.description,
    author = this.author,
    image = this.image,
    published = this.published,
    url = this.url
)
