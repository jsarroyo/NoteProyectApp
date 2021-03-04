package com.example.movieapp.bussinesIntel

import android.content.Context
import com.example.movieapp.MainActivity
import com.example.movieapp.database.LocalDatabase
import com.example.movieapp.model.Moovie_DB
import kotlinx.android.synthetic.main.item_list.*

class Moovie_BI (){

    private val contextInstance = MainActivity.applicationContext()

    fun insertMoovies(mov: Moovie_DB){
        Thread(
                Runnable {
                    LocalDatabase.getInstance(contextInstance).movieDao.insertMovie(mov)
                }
        ).start()
    }
    fun deleteMoovies(mov: Moovie_DB){
        Thread(
                Runnable {
                    LocalDatabase.getInstance(contextInstance).movieDao.deleteMovie(mov)
                    getAllMoovies()
                }
        ).start()
    }
    fun getAllMoovies(){
        Thread(
                Runnable {
                    MainActivity.mMovie = LocalDatabase.getInstance(contextInstance).movieDao.getAllMovies().toMutableList() as ArrayList<Moovie_DB>
                }
        ).start()
    }
}