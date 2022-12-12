package com.example.workfour

import com.example.workfour.room.NoteData

data class Note(val id: Int, var name: String, var price: Double, var description: String, var img: String, var favorite: Boolean) :
        (NoteData) -> Unit {



    fun getPriceString(): String {
        return "$ $price"
    }

    override fun invoke(p1: NoteData) {
        TODO("Not yet implemented")
    }
}