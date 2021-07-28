package com.example.rappitest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rappitest.movies.model.ClsMovie

@Database(version = 1,
    entities = [ClsMovie::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}