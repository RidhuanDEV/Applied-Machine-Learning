package com.dicoding.asclepius.data.response

class HistoryResponse (
    val status: String,
    val totalResults: Int,
    val articles: List<Article> = listOf()
)

data class Article(
    val title: String? = "",
    val description: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
)
