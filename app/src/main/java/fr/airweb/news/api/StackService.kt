package fr.airweb.news.api



import fr.airweb.news.models.News
import retrofit2.Call
import retrofit2.http.*

interface StackService {



    @GET("psg/psg.json")
    fun getNews(): Call<News>

}