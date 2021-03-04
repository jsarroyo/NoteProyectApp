
package com.example.movieapp.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.model.Moovie_DB
import com.example.movieapp.model.Note_DB

@Dao
interface NoteDatabaseDao {
    @Insert
    fun insertNote (newMovie: Note_DB)

    @Delete
    fun deleteNote (deletableMovie: Note_DB)

    @Query("Select * From Notes_tbl ORDER BY priority desc, id ")
    fun getAllNotes ():List<Note_DB>

    @Query("SELECT * FROM Notes_tbl WHERE id =:key")
    fun getNote (key: Int):Note_DB?
}