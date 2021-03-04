package com.example.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.dao.MovieDatabaseDao
import com.example.movieapp.dao.NoteDatabaseDao
import com.example.movieapp.model.Moovie_DB
import com.example.movieapp.model.Note_DB


@Database(entities = [Moovie_DB::class,Note_DB::class],version = 1,exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {
    abstract  val movieDao: MovieDatabaseDao
    abstract  val noteDao: NoteDatabaseDao

    companion object{
        @Volatile
        private var dbInstance: LocalDatabase? =null
        //patron singleton
        //Me aseguro que un  referencia estatica que solo pueda existir una unica vez en el proyecto
        fun getInstance(context: Context):LocalDatabase
        {
            synchronized(this){
                var instance = dbInstance

                if (instance== null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        "local_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    dbInstance = instance
                }
                return instance
            }
        }
    }
}