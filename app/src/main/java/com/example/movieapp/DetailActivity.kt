package com.example.movieapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.movieapp.bussinesIntel.Moovie_BI
import com.example.movieapp.model.Moovie_DB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_det.*
import kotlinx.android.synthetic.main.activity_serie_detail.*
import kotlinx.android.synthetic.main.fragment_serie_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_detail)

        val nMoovieIndex = intent.getIntExtra("index",0)
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")
        val season = intent.getStringExtra("season")
        val description = intent.getStringExtra("description")
        val url = intent.getStringExtra("url")

        btn_delete_movie_adt.setOnClickListener {

            val vloDD = Moovie_BI()
            vloDD.deleteMoovies(
                            MainActivity.mMovie[nMoovieIndex]

            )

            Toast.makeText(this,R.string.msgMovieDeleted, Toast.LENGTH_LONG).show();

        }



        val mName = findViewById<TextView>(R.id.nameTextView)
        val mAuthor = findViewById<TextView>(R.id.authorTextView)
        val mSeason = findViewById<TextView>(R.id.seasonTextView)
        val mDescription = findViewById<TextView>(R.id.descriptionTextView)
        val mPoster = findViewById<ImageView>(R.id.posterImageView)
        val mVistaImageView = findViewById<ImageView>(R.id.vistaImageView)

        mName.text = name
        mAuthor.text = author
        mSeason.text = season
        mDescription.text = description

        Glide.with(this)
            .load(url)
            .into(mPoster)

        Glide.with(this)
            .load(url)
            .into(mVistaImageView)
    }

}
