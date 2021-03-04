

package com.example.movieapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.bussinesIntel.Moovie_BI
import com.example.movieapp.bussinesIntel.Note_BI
import com.example.movieapp.model.Moovie_DB
import com.example.movieapp.model.Note_DB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.activity_det_note.*

class AddNote : AppCompatActivity() {

    private lateinit  var tvNote: EditText
    private lateinit  var tvMovieAuthor: EditText
    private lateinit  var tvMovieDescription: EditText
    private lateinit  var tvMovieUrlPoster: EditText
    private lateinit  var btnAddMoovie: Button
    private var vlbPriority : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


        imgAddNoteBack.setOnClickListener{
            Toast.makeText(this,R.string.msgBackButton, Toast.LENGTH_LONG).show();
            finish()
        }

        btnAddNoteLowPriority.setOnClickListener{
            vlbPriority = false
        }
        btnAddNoteMaxPriority.setOnClickListener{
            vlbPriority = true
        }

        imgAddNoteDone.setOnClickListener{
            if (etAddNoteTitle.text.toString()== "" || etAddNoteSubTitle.text.toString() == ""||etAddNoteDesc.text.toString()== "")
            {
                Toast.makeText(this,R.string.msgEmptyFields, Toast.LENGTH_LONG).show();

            }
            else{
                var vloInserted = Note_DB(  etAddNoteTitle.text.toString()
                        , etAddNoteSubTitle.text.toString()
                        , etAddNoteDesc.text.toString()
                        , tvAddNoteWebLink.text.toString()
                        ,0
                        ,vlbPriority
                )
                val vloDD = Note_BI()
                vloDD.insertNote(vloInserted)
                MainActivity.mListNotes.add(vloInserted)

                Toast.makeText(this,R.string.msgMovieSaved, Toast.LENGTH_LONG).show();
                finish()
            }


        }

        imgAddNoteBack.setOnClickListener{
            Toast.makeText(this,R.string.msgBackButton, Toast.LENGTH_LONG).show();
            finish()
        }

//        tvMovieName = findViewById(R.id.txv_add_moovie_name) as EditText
//        tvMovieAuthor = findViewById(R.id.txv_add_moovie_author) as EditText
//        tvMovieDescription = findViewById(R.id.txv_add_moovie_description) as EditText
//        tvMovieUrlPoster = findViewById(R.id.txv_add_UrlImagen) as EditText
    }

}