package com.example.movieapp.bussinesIntel

import android.content.Context
import com.example.movieapp.MainActivity
import com.example.movieapp.database.LocalDatabase
import com.example.movieapp.model.Moovie_DB
import com.example.movieapp.model.Note_DB
import kotlinx.android.synthetic.main.item_list.*

class Note_BI (){

    private val contextInstance = MainActivity.applicationContext()

    fun insertNote(mov: Note_DB){
        Thread(
            Runnable {
                LocalDatabase.getInstance(contextInstance).noteDao.insertNote(mov)
            }
        ).start()
    }
    fun deleteNotes(mov: Note_DB){
        Thread(
            Runnable {
                LocalDatabase.getInstance(contextInstance).noteDao.deleteNote(mov)
                getAllNotes()
            }
        ).start()
    }
    fun getAllNotes(){
        Thread(
            Runnable {
                MainActivity.mListNotes = LocalDatabase.getInstance(contextInstance).noteDao.getAllNotes().toMutableList() as ArrayList<Note_DB>
            }
        ).start()
    }
}