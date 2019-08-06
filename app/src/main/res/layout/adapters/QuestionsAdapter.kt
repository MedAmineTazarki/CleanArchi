package fr.airweb.news.adapters

import androidx.recyclerview.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


import com.tazarki.ws.models.Article
import fr.airweb.news.R
import kotlinx.android.synthetic.main.item_news.view.*

class QuestionsAdapter (private val article: List<Article?>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var mOnItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(article[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtQuestion: TextView = itemView.textQuestion
        var txtName: TextView = itemView.textName
        var imgProfil: ImageView = itemView.imgProfil


        fun bindItems(item: Article?) {
            txtQuestion.text = Html.fromHtml(item?.title ?: "")
            txtName.text = item?.content

            Picasso.get().load(item?.picture).into(imgProfil)

                // itemView.setOnClickListener { mOnItemClickListener.onItemClick(item) }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(item: Article?)
    }

    override fun getItemCount() = article.size

}
