package fr.airweb.news


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle

import android.util.Log
import android.view.Menu

import androidx.appcompat.app.AppCompatActivity
import android.app.SearchManager;
import android.view.MenuItem

import android.widget.SearchView.OnQueryTextListener;
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat


import fr.airweb.news.adapters.NewsAdapter
import fr.airweb.news.api.RetrofitClient
import fr.airweb.news.models.Article
import fr.airweb.news.models.News
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import androidx.core.view.MenuItemCompat.setOnActionExpandListener
import androidx.core.view.MenuItemCompat.setOnActionExpandListener
import com.lmntrx.android.library.livin.missme.ProgressDialog


class MainActivity : AppCompatActivity(){

    private lateinit var searchView: SearchView
    lateinit var adapterNews : NewsAdapter
    var newsList : MutableList<Article> = ArrayList()

    lateinit var   progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         progressDialog = ProgressDialog(this)
         progressDialog.setMessage("Telechargement...");




        if (isNetworkAvailable(this)){

            loadJSON()
        }else{
            Log.d("allArticle", App.database.ArticleDao().allArticle.get(0).picture);


             adapterNews =App.database.ArticleDao().allArticle.let { NewsAdapter(it) }

            recyclerView.adapter = adapterNews

        }


    }

        fun loadJSON() {

            progressDialog.show();

            RetrofitClient().getClient().getNews().enqueue(object : retrofit2.Callback<News> {

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if (response.isSuccessful) {
                        progressDialog.dismiss()

                          newsList= response.body()?.articles as MutableList<Article>
                          adapterNews = newsList.let { NewsAdapter(it) }!!
                          recyclerView.adapter = adapterNews



                        App.database.ArticleDao().deleteAll()
                        App.database.ArticleDao().insert(response.body()?.articles as List<Article>)

                   //     Log.d("allArticle", App.database.ArticleDao().allArticle.get(0).picture);



                    } else


                    Log.d("not good response", "not good response")
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    // toast("fail")
                }

            })



        }


        fun isNetworkAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            activeNetworkInfo = cm.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }






    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setSubmitButtonEnabled(true)
        searchView.setQueryHint("Recherche ")


        val newsListFiler : MutableList<Article> = ArrayList()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {

                for (item: Article in newsList) {

                    if (item.title!!.toLowerCase()?.contains(query.toLowerCase())!!){

                        newsListFiler.add(item)
                    }
                }



                newsList.clear()
                newsList.addAll(newsListFiler)
                newsListFiler.clear()
                adapterNews = newsList.let { NewsAdapter(it) }!!
                recyclerView.adapter = adapterNews
                return true
            }



        })


        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {


                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                  loadJSON()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


}

