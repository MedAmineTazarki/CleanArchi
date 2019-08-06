package fr.airweb.news

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.airweb.news.models.Article
import fr.airweb.news.models.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ArticleDao(): ArticleDao
}