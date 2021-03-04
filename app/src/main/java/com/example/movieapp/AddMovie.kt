package com.example.movieapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.bussinesIntel.Moovie_BI
import com.example.movieapp.model.Moovie_DB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovie : AppCompatActivity() {

    private lateinit  var tvMovieName: EditText
    private lateinit  var tvMovieAuthor: EditText
    private lateinit  var tvMovieDescription: EditText
    private lateinit  var tvMovieUrlPoster: EditText
    private lateinit  var btnAddMoovie: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        val btn_click_me = findViewById(R.id.btnAddMovie) as Button

        btn_click_me.setOnClickListener {

            val vlcName = tvMovieName.text.toString()
            val vlcAuthor = tvMovieAuthor.text.toString()
            val vlcDescription = tvMovieDescription.text.toString()
            val vlcUrl = tvMovieUrlPoster.text.toString()
            if (vlcName == "" || vlcAuthor == "" || vlcDescription == "" || vlcUrl == "") {
                Toast.makeText(
                    this@AddMovie,
                    R.string.msgEmptyFields,
                    Toast.LENGTH_LONG
                ).show();
                return@setOnClickListener
            }

            MainActivity.mMovie.add(
                    Moovie_DB(
                        tvMovieName.text.toString(),
                        tvMovieAuthor.text.toString(),
                        1,
                        tvMovieDescription.text.toString(),
                        0,
                        tvMovieUrlPoster.text.toString()
                    )
            )

            val vloDD = Moovie_BI()
            vloDD.insertMoovies(
                    Moovie_DB(
                        tvMovieName.text.toString(),
                        tvMovieAuthor.text.toString(),
                        1,
                        tvMovieDescription.text.toString(),
                        0,
                        tvMovieUrlPoster.text.toString()
                    )
            )

            Toast.makeText(this, R.string.msgMovieSaved, Toast.LENGTH_LONG)
                .show()
            finish()

        }

        tvMovieName = findViewById(R.id.txv_add_moovie_name) as EditText
        tvMovieAuthor = findViewById(R.id.txv_add_moovie_author) as EditText
        tvMovieDescription = findViewById(R.id.txv_add_moovie_description) as EditText
        tvMovieUrlPoster = findViewById(R.id.txv_add_UrlImagen) as EditText
    }

}