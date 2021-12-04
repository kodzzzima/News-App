package com.example.latestnews.domain.news

/**
 * to display in detailsFragment
 */
data class News(
    var id: Int = 0,
    var newsId: String = "",
    var title: String = "",
    var description: String = "",
    var author: String = "",
    var image: String = "",
    var published: String = "",
    var url: String = "",
)