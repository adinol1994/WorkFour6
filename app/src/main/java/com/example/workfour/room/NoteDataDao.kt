package com.example.workfour.room


import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Dao

@Dao
interface NoteDataDao {

    @Query("select * from NoteData")
    fun getAll(): LiveData<List<NoteData>>

    @Query("select * from NoteData where  id = :noteId")
    suspend fun getById(noteId: Int): NoteData

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: NoteData)


}