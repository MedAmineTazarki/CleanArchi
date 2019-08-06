package fr.airweb.news


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

import kotlinx.android.synthetic.main.activity_detail.imageDetail




class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        var intent = intent
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val image = intent.getStringExtra("image")
        val type = intent.getStringExtra("type")



        titleTextview.text=title
         descriptionDetail.text = content



        Picasso.get()
            .load(image)
            .placeholder(R.drawable.placeholderr)
            .into(imageDetail)





    }




}
