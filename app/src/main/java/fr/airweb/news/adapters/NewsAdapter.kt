package fr.airweb.news.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.airweb.news.DetailActivity


import fr.airweb.news.R
import fr.airweb.news.models.Article
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter (private val article: List<Article?>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(article[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtTitle: TextView = itemView.text_title
        var txtDes: TextView = itemView.text_des
        var img: ImageView = itemView.img


        fun bindItems(item: Article?) {
            txtTitle.text = Html.fromHtml(item?.title ?: "")
            txtDes.text = item?.content

            Picasso.get().load(item?.picture).into(img)


            itemView.setOnClickListener {


                val intent = Intent(itemView.context, DetailActivity::class.java)

                intent.putExtra("content",item?.content)
                intent.putExtra("title", item?.title)
                intent.putExtra("image", item?.picture)
                intent.putExtra("type", item?.type)


                itemView.context.startActivity(intent)

            }
        }
    }




    override fun getItemCount() = article.size

}
