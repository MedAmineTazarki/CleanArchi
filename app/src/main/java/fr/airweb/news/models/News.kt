package fr.airweb.news.models

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("news")
    val articles: List<Article?>?
)