package com.example.workfour

import com.example.workfour.room.NoteData
import com.example.workfour.room.NoteDataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NoteRepository(private val dao: NoteDataDao) {


    private val list = dao.getAll()

    fun getAll() = list

    suspend fun insert(vararg note: NoteData){
        withContext(Dispatchers.IO){
            note.forEach {
                dao.insert(it)
            }
        }
    }

    suspend fun getById(id: Int): NoteData {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getById(id)
        }
    }

}