package com.example.movieapp.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.model.Moovie_DB

@Dao
interface MovieDatabaseDao {
    @Insert
    fun insertMovie (newMovie: Moovie_DB)

    @Delete
    fun deleteMovie (deletableMovie: Moovie_DB)

    @Query("Select * From Moovie_tbl")
    fun getAllMovies ():List<Moovie_DB>

    @Query("SELECT * FROM Moovie_tbl WHERE id =:key")
    fun getMovie (key: Long):Moovie_DB?
}