package fr.airweb.news.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ArticleDao {

    @get:Query("SELECT * from article ORDER BY title ASC")
    val allArticle: List<Article>

    @get:Query("SELECT * from article WHERE title")
    val allArticleFormTitle: List<Article>

    @Insert
    fun insert(article: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAll()
}