package com.example.workfour.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteData::class], version = 1)
abstract class DbConnection : RoomDatabase() {
    abstract fun entityDao(): NoteDataDao
}