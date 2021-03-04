package com.example.movieapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName ="Moovie_tbl")
data class Moovie_DB(

    @ColumnInfo(name= "moovie_name")
    val name: String,

    @ColumnInfo(name= "moovie_author")
    val author: String,

    @ColumnInfo(name= "moovie_season")
    val season: Int,

    @ColumnInfo(name= "moovie_description")
    val description: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long=0L,

    @ColumnInfo(name= "moovie_url")
    val url:String):Serializable