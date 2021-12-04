package com.example.latestnews.data.remote.models

import com.google.gson.annotations.SerializedName

data class LatestNewsDto(

    @SerializedName("author")
    val author: String = "",

    @SerializedName("category")
    val category: List<String> = emptyList(),

    @SerializedName("description")
    val description: String = "",

    @SerializedName("id")
    val newsId: String = "",

    @SerializedName("image")
    val image: String = "",

    @SerializedName("language")
    val language: String = "",

    @SerializedName("published")
    val published: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("url")
    val url: String = ""
)