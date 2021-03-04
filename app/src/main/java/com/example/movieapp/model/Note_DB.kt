package com.example.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName ="Notes_tbl")
data class Note_DB(

    @ColumnInfo(name= "title")
    val title: String,

    @ColumnInfo(name= "subtitle")
    val subtitle: String,

    @ColumnInfo(name= "description")
    val description: String,

    @ColumnInfo(name= "url")
    val url: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    @ColumnInfo(name= "priority")
    val priority:Boolean): Serializable