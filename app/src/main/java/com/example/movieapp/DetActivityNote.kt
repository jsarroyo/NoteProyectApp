package com.example.movieapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.example.movieapp.bussinesIntel.Note_BI
import com.example.movieapp.model.Note_DB
import kotlinx.android.synthetic.main.activity_det.*
import kotlinx.android.synthetic.main.activity_det_note.*
import kotlinx.android.synthetic.main.activity_note_detail.*
import kotlinx.android.synthetic.main.fragment_note_detail.*
import kotlinx.android.synthetic.main.fragment_serie_detail.*


class DetActivityNote : AppCompatActivity() {
    private var vlbPriority : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_det_note)

        val nMoovieIndex = intent.getIntExtra("index",0)
        val cTitle = intent.getStringExtra("Title")
        val cSubTitle = intent.getStringExtra("Subttitle")
        val cDateTime = intent.getStringExtra("DateTime")
        val cUrl = intent.getStringExtra("Url")
        val cDescription = intent.getStringExtra("Description")
        val cImgNote = intent.getStringExtra("imgNote")
        vlbPriority = (intent.getStringExtra("Priority") == "true" )

        val mTitle = findViewById<TextView>(R.id.etNoteTitle)
        val mDateTime = findViewById<TextView>(R.id.tvDateTime)
        val mSubTitle = findViewById<TextView>(R.id.etNoteSubTitle)
        val mDescription = findViewById<TextView>(R.id.etNoteDesc)
        val mUrl = findViewById<TextView>(R.id.tvWebLink)
        val mVistaImageView = findViewById<ImageView>(R.id.imgNote)

        btnMaxPriority.setOnClickListener{
            vlbPriority = true
        }
        btnLowPriority.setOnClickListener{
            vlbPriority = false
        }
        imgMore.setOnClickListener{
            DeleteNote(nMoovieIndex)
        }
//        val image_view = findViewById<ImageView>(R.id.imgMore)
//
//        image_view.setOnClickListener {
//            DeleteNote(nMoovieIndex)
//        }
//        imgMoreLands.setOnClickListener{
//            DeleteNote(nMoovieIndex)
//        }
//        btn_delete_movie_fsd.setOnClickListener{
//            DeleteNote(nMoovieIndex)
//        }
//        imgMoreHoriz.setOnClickListener{
//            DeleteNote(nMoovieIndex)
//        }

        imgDone.setOnClickListener{

            if (mTitle.text.toString()== "" || mSubTitle.text.toString() == ""||mDescription.text.toString()== "")
            {
                Toast.makeText(this,R.string.msgEmptyFields, Toast.LENGTH_LONG).show()
            }
            else{
                val vloClase = MainActivity.mListNotes[nMoovieIndex]

                MainActivity.mListNotes.remove(vloClase)

                val vloDD = Note_BI()
                vloDD.deleteNotes(
                        vloClase
                )
                var vloInserted = Note_DB(  mTitle.text.toString()
                        , mSubTitle.text.toString()
                        , mDescription.text.toString()
                        , mUrl.text.toString()
                        ,0
                        ,vlbPriority
                )
                vloDD.insertNote(vloInserted)
                MainActivity.mListNotes.add(vloInserted)

                Toast.makeText(this,R.string.msgMovieSaved, Toast.LENGTH_LONG).show();
                finish()
            }

        }
        imgBack.setOnClickListener{
            Toast.makeText(this,R.string.msgBackButton, Toast.LENGTH_LONG).show();
            finish()
        }

        mTitle.text = cTitle
        mDateTime.text = cDateTime
        mSubTitle.text = cSubTitle
        mDescription.text = cDescription
        mUrl.text = cUrl

//        titleTextView.text = cTitle
//        subtitleTextView.text = cSubTitle
//        descriptionTextView2.text = cDescription


        if (cUrl!=""){
            Glide.with(this).load(cUrl).preload()
            Glide.with(this)
                    .load(cUrl) //Carga la imagen
                    .override(300) //limita el tamano de la imagen a 300 x 300 || size * size || width * height
                    .thumbnail(0.5f)//carga una imagen de menor calidad mientras la imagen real esta lista
                    .placeholder(R.drawable.movie_preview) //selecciona una imagen por defecto mientras se carga la imagen
                    .error(R.drawable.movie_preview) //selecciona una imagen por defecto, en caso de error
                    .into(mVistaImageView) //objeto donde se incrusta


            Glide.with(this)
                    .load(cUrl)
                    .override(300) //limita el tamano de la imagen a 300 x 300 || size * size || width * height
                    .thumbnail(0.5f)//carga una imagen de menor calidad mientras la imagen real esta lista
                    .placeholder(R.drawable.movie_preview) //selecciona una imagen por defecto mientras se carga la imagen
                    .error(R.drawable.movie_preview) //selecciona una imagen por defecto, en caso de error
                    .into(mVistaImageView)
        }

    }

    private fun DeleteNote(nMoovieIndex: Int) {
        val vloClase = MainActivity.mListNotes[nMoovieIndex]

        MainActivity.mListNotes.remove(vloClase)

        val vloDD = Note_BI()
        vloDD.deleteNotes(
            vloClase
        )
        Toast.makeText(this,R.string.msgMovieDeleted, Toast.LENGTH_LONG).show();
        finish()
    }
}