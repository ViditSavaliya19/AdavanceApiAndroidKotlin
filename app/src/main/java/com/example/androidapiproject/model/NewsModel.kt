package com.example.androidapiproject.model

data class NewsModel(
	val totalResults: Int? = null,
	val articles: List<ArticlesItem?>? = null,
	val status: String? = null
)

data class ArticlesItem(
	val publishedAt: String? = null,
	val author: String? = null,
	val urlToImage: Any? = null,
	val description: Any? = null,
	val source: Source? = null,
	val title: String? = null,
	val url: String? = null,
	val content: Any? = null
)

data class Source(
	val name: String? = null,
	val id: String? = null
)

