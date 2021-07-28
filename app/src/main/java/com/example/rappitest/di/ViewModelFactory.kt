package com.example.rappitest.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.rappitest.database.AppDatabase
import com.example.rappitest.movies.viewModel.MoviesListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            val db = Room.databaseBuilder(
                activity.applicationContext,
                AppDatabase::class.java,
                "ClsMovie"
            ).build()
            @Suppress("UNCHECKED_CAST")
            return MoviesListViewModel(db.MovieDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}