package fr.airweb.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "article")
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id :Int ,


    @SerializedName("title")
    val title: String?,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("dateformated")
    val type: String?,

    @SerializedName("type")
    val dateformated: String?
)