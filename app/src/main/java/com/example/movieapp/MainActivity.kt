package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.bussinesIntel.Moovie_BI
import com.example.movieapp.bussinesIntel.Note_BI
import com.example.movieapp.model.Moovie_DB
import com.example.movieapp.model.Note_DB
import kotlinx.android.synthetic.main.activity_add_note.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.note_list_content.view.*


class MainActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    private var defMoviesInserted: Boolean = false

    companion object{
        var mMovie:ArrayList<Moovie_DB> = arrayListOf()
        var mListNotes:ArrayList<Note_DB> = arrayListOf()
        private var instance: MainActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


    init {
        instance = this
    }

    override fun onResume() {
        super.onResume()

        setupRecyclerView(mRecyclerView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (!defMoviesInserted){

            //setMoovies()
            setNotes()
        }


        btnGoToAddMovie.setOnClickListener {
            val intent = Intent(this, AddNote::class.java)
            startActivity(intent)

            val vlodat = Note_BI()
            vlodat.getAllNotes()
            setupRecyclerView(mRecyclerView)
        }

        if (detailFrameLayout != null) {
            twoPane = true

            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {

                    putInt("index",0)
                    putString("Title","")
                    putString("Subttitle","")
                    //putString("DateTime","")
                    putString("Url","")
                    putString("description","")

                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.detailFrameLayout, fragment)
                    .commit()
        }
        setupRecyclerView(mRecyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        var vloAdapter = SerieViewAdapter(this, mListNotes, twoPane, recyclerView)
        recyclerView.adapter = vloAdapter
        vloAdapter.notifyDataSetChanged()
    }

    class SerieViewAdapter(private val parentActivity: MainActivity,
                           private val mListNotesValues: ArrayList<Note_DB>,
                           private val twoPane: Boolean,
                           private val recyclerView: RecyclerView):
            RecyclerView.Adapter<SerieViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->

                if (twoPane) {
                    val fragment = DetailFragment().apply {
                        arguments = Bundle().apply {
                            putInt("index",recyclerView.getChildAdapterPosition(v))
                            putString("Title",mListNotes[recyclerView.getChildAdapterPosition(v)].title)
                            putString("Subttitle",mListNotes[recyclerView.getChildAdapterPosition(v)].subtitle)
                            //putString("DateTime",mListNotes[recyclerView.getChildAdapterPosition(v)].datetime.toString())
                            putString("Url",mListNotes[recyclerView.getChildAdapterPosition(v)].url)
                            putString("description",mListNotes[recyclerView.getChildAdapterPosition(v)].description)
                            //putString("imgNote",mListNotes[recyclerView.getChildAdapterPosition(v)].imgNote)

                        }
                    }
                    parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.detailFrameLayout, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, DetActivityNote::class.java).apply {

                        putExtra("index",recyclerView.getChildAdapterPosition(v))
                        putExtra("Title",mListNotes[recyclerView.getChildAdapterPosition(v)].title)
                        putExtra("Subttitle",mListNotes[recyclerView.getChildAdapterPosition(v)].subtitle)
                        //putExtra("DateTime",mListNotes[recyclerView.getChildAdapterPosition(v)].datetime.toString())
                        putExtra("Url",mListNotes[recyclerView.getChildAdapterPosition(v)].url)
                        putExtra("Description",mListNotes[recyclerView.getChildAdapterPosition(v)].description)
                        //putExtra("imgNote",mListNotes[recyclerView.getChildAdapterPosition(v)].imgNote)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.note_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val noteObj = mListNotes[position]
 //           holder.mPosterImageView?.let {
//                Glide.with(holder.itemView.context).load(noteObj.url).preload()
//
//                Glide.with(holder.itemView.context)
//                            .load(noteObj.url)//Carga la date de la imagen
//                            .override(300) //limita el tamano de la imagen a 300 x 300 || size * size || width * height
//                            .thumbnail(0.5f)//carga una imagen de menor calidad mientras la imagen real esta lista
//                            .placeholder(R.drawable.movie_preview) //selecciona una imagen por defecto mientras se carga la imagen
//                            .error(R.drawable.movie_preview) //selecciona una imagen por defecto, en caso de error
//                            .into(it)//objeto donde se incrusta

//Explicacion usar PICASSO
//                            imageview.run{
//                                Picasso.get().load(url).fetch()
//                                Picasso.get()
//                                        .load(movie.url)  //Carga la date de la imagen
//                                        .resize(300)    //limita el tamano de la imagn a 300 x 300 || size * size || width * height
//                                        .error(R.drawable.movie_preview) //selecciona una imagen por defecto, en caso de error
//                                        .into(it)//objeto donde se incrusta
//                            }

//            }
            with(holder.itemView) {
                tag = noteObj
                //rootView.setBackgroundColor()
                //
                //
                if( noteObj.priority){
                    etListContentNoteTitle.setBackgroundColor(resources.getColor(R.color.colorAccent))
                    etListContentNoteDescription.setBackgroundColor(resources.getColor(R.color.colorAccent))
                }else{
                    etListContentNoteTitle.setBackgroundColor(resources.getColor(R.color.ColorYellow))
                    etListContentNoteDescription.setBackgroundColor(resources.getColor(R.color.ColorYellow))
                }

                etListContentNoteTitle.text = noteObj.title
                etListContentNoteDescription.text = noteObj.description
                setOnClickListener(onClickListener)
            }



        }

        override fun getItemCount():Int
        {
            val vlodat = Note_BI()
            vlodat.getAllNotes()
            return mListNotes.size
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            //val mPosterImageView: ImageView? = view.posterImageView
        }
    }

    private fun setMoovies(){
        val vlodat = Moovie_BI()

        vlodat.getAllMoovies()

        if (mMovie.size == 0)
        {
            val vloDD = Moovie_BI()

            vloDD.insertMoovies(Moovie_DB("joker", "Todd Phillips", 1,"Situada en los años 80′. Un cómico fallido es arrastrado a la locura, convirtiendo su vida en una vorágine de caos y delincuencia que poco a poco lo llevará a ser el psicópata criminal más famoso de Gotham.",0,"https://image.tmdb.org/t/p/w185_and_h278_bestv2/v0eQLbzT6sWelfApuYsEkYpzufl.jpg"))

            vloDD.insertMoovies(Moovie_DB("Parasite", "Bong Joon-ho", 1,"anto Gi Taek (Song Kang Ho) como su familia están sin trabajo. Cuando su hijo mayor, Gi Woo (Choi Woo Shik), empieza a recibir clases particulares en casa de Park (Lee Sun Gyun), las dos familias, que tienen mucho en común pese a pertenecer a dos mundos totalmente distintos, comienzan una interrelación de resultados impresivibles.",0,"https://cuevana3.io/wp-content/uploads/2019/08/parasite-20039-poster-211x300.jpg"))

            vloDD.insertMoovies(Moovie_DB("Bohemian Rhapsody", "Aaron McCusker",1,"Bohemian Rhapsody es una rotunda y sonora celebración de Queen, de su música y de su extraordinario cantante Freddie Mercury, que desafió estereotipos e hizo añicos tradiciones para convertirse en uno de los showmans más queridos del mundo. La película plasma el meteórico ascenso al olimpo de la música de la banda a través de sus icónicas canciones y su revolucionario sonido, su crisis cuando el estilo de vida de Mercury estuvo fuera de control, y su triunfal reunión en la víspera del Live Aid, en la que Mercury, mientras sufría una enfermedad que amenazaba su vida, lidera a la banda en uno de los conciertos de rock más grandes de la historia. Veremos cómo se cimentó el legado de una banda que siempre se pareció más a una familia, y que continúa inspirando a propios y extraños, soñadores y amantes de la música hasta nuestros días.",0,"https://cuevana3.io/wp-content/uploads/2018/11/bohemian-rhapsody-7089-poster-208x300.jpg"))

            vloDD.insertMoovies(Moovie_DB("John Wick", "Chad Stahelski, David Leitch",1, "En Nueva York, John Wick, un asesino a sueldo retirado, vuelve otra vez a la acción para vengarse de los gángsters que le quitaron todo.",0,"https://cuevana3.io/wp-content/uploads/2019/05/john-wick-3-parabellum-14464-poster-200x300.jpg"))

            vloDD.insertMoovies(Moovie_DB("The Flash", "Glen Winter",1 ,"Nueve meses después de que el laboratorio S.T.A.R. explotara, Barry despierta del coma y descubre que tiene el poder de la súper velocidad. Con la ayuda de su nuevo equipo, Barry, denominado ahora `Flash', luchará contra el crimen en Ciudad Central.",0,"https://www.formulatv.com/images/series/posters/800/834/7_m1.jpg"))

            Toast.makeText(this, R.string.msgDefaultMovieSaved, Toast.LENGTH_LONG)
                    .show()
            defMoviesInserted = true
        }
    }
    private fun setNotes(){
        val vlodat = Note_BI()

        vlodat.getAllNotes()

        if (mListNotes.size == 0)
        {
            val vloDD = Note_BI()

            vloDD.insertNote(Note_DB("joker", "Todd Phillips", "ver peli","https://image.tmdb.org/t/p/w185_and_h278_bestv2/v0eQLbzT6sWelfApuYsEkYpzufl.jpg",0,true))

            vloDD.insertNote(Note_DB("Parasite", "Bong Joon-ho", "ver peli","https://cuevana3.io/wp-content/uploads/2019/08/parasite-20039-poster-211x300.jpg",0,false))

            vloDD.insertNote(Note_DB("Bohemian Rhapsody", "Aaron McCusker","ver peli","https://cuevana3.io/wp-content/uploads/2018/11/bohemian-rhapsody-7089-poster-208x300.jpg",0,true))

            vloDD.insertNote(Note_DB("John Wick", "Chad Stahelski, David Leitch","ver peli", "En Nueva York, John Wick, un asesino a sueldo retirado, vuelve otra vez a la acción para vengarse de los gángsters que le quitaron todo.",0,false))

            vloDD.insertNote(Note_DB("The Flash", "Glen Winter","ver peli","https://www.formulatv.com/images/series/posters/800/834/7_m1.jpg",0,true))

            Toast.makeText(this, R.string.msgDefaultMovieSaved, Toast.LENGTH_LONG)
                .show()
            defMoviesInserted = true
        }
    }
}


