package com.example.myapplication

import com.google.gson.annotations.SerializedName


data class NewsResponse(
    @SerializedName("status")
    val status: String,

    // Matches the "news" field from the currentsapi JSON
    @SerializedName("news")
    val articles: List<Article>
)


data class Article(
    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String,

    // Field name matches "image" from currentsapi
    @SerializedName("image")
    val urlToImage: String?,

    @SerializedName("published")
    val publishedAt: String,

    @SerializedName("content")
    val content: String?
)
