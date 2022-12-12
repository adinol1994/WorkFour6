package com.example.workfour.room



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var price: Double,
    var description: String,
    val img: String,
    var favorite: Boolean
)