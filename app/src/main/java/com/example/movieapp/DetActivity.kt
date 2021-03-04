package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.movieapp.bussinesIntel.Moovie_BI
import com.example.movieapp.model.Moovie_DB
import kotlinx.android.synthetic.main.activity_det.*
import kotlinx.android.synthetic.main.activity_serie_detail.*
import kotlinx.android.synthetic.main.fragment_serie_detail.*

class DetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_det)

        val nMoovieIndex = intent.getIntExtra("index",0)
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")
        val season = intent.getStringExtra("season")
        val description = intent.getStringExtra("description")
        val url = intent.getStringExtra("url")

        val mName = findViewById<TextView>(R.id.nameTextView)
        val mAuthor = findViewById<TextView>(R.id.authorTextView)
        val mSeason = findViewById<TextView>(R.id.seasonTextView)
        val mDescription = findViewById<TextView>(R.id.descriptionTextView)
        val mPoster = findViewById<ImageView>(R.id.posterImageView)
        val mVistaImageView = findViewById<ImageView>(R.id.vistaImageView)

        btn_delete_movie_adt.setOnClickListener {
            val vloClase = MainActivity.mMovie[nMoovieIndex]

            MainActivity.mMovie.remove(vloClase)

            val vloDD = Moovie_BI()
            vloDD.deleteMoovies(
                    vloClase
            )

            Toast.makeText(this,R.string.msgMovieDeleted, Toast.LENGTH_LONG).show();
            finish()
        }


        mName.text = name
        mAuthor.text = author
        mSeason.text = season
        mDescription.text = description

        Glide.with(this).load(url).preload()
        Glide.with(this)
                .load(url) //Carga la imagen
                .override(300) //limita el tamano de la imagen a 300 x 300 || size * size || width * height
                .thumbnail(0.5f)//carga una imagen de menor calidad mientras la imagen real esta lista
                .placeholder(R.drawable.movie_preview) //selecciona una imagen por defecto mientras se carga la imagen
                .error(R.drawable.movie_preview) //selecciona una imagen por defecto, en caso de error
                .into(mPoster) //objeto donde se incrusta


        Glide.with(this)
                .load(url)
                .override(300) //limita el tamano de la imagen a 300 x 300 || size * size || width * height
                .thumbnail(0.5f)//carga una imagen de menor calidad mientras la imagen real esta lista
                .placeholder(R.drawable.movie_preview) //selecciona una imagen por defecto mientras se carga la imagen
                .error(R.drawable.movie_preview) //selecciona una imagen por defecto, en caso de error
                .into(mVistaImageView)
    }
}