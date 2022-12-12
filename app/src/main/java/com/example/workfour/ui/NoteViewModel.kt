package com.example.workfour.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.workfour.NoteRepository
import com.example.workfour.room.NoteData
import com.example.workfour.room.DbConnection
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var note: LiveData<List<NoteData>>? = null

    var selectedNote: MutableLiveData<NoteData> = MutableLiveData()

    private val repository: NoteRepository
    init {
        val dao = Room.databaseBuilder(application.applicationContext, DbConnection::class.java, "db")
            .build()
            .entityDao()
        repository = NoteRepository(dao)
    }


    fun getAll(): LiveData<List<NoteData>>? {
        if (note == null){
            note = repository.getAll()
        }
        return note
    }

    fun insert(vararg noteData: NoteData){
        viewModelScope.launch {
            repository.insert(*noteData)
        }
    }

    fun getById(id: Int) {
        viewModelScope.launch {
            val note = repository.getById(id)
            selectedNote.value = note
        }
    }

}