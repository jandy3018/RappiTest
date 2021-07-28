package com.example.rappitest.di.base

import androidx.lifecycle.ViewModel
import com.example.rappitest.di.component.DaggerViewModelInjector
import com.example.rappitest.di.component.ViewModelInjector
import com.example.rappitest.di.module.NetworkModule
import com.example.rappitest.movies.viewModel.MostPopularViewModel
import com.example.rappitest.movies.viewModel.MoviesListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        injectModel()
    }

    /**
     * Injects the required dependencies
     */
    private fun injectModel() {
        when (this) {
            is MoviesListViewModel -> injector.inject(this)
            is MostPopularViewModel -> injector.inject(this)
        }
    }
}