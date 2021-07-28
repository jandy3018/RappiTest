package com.example.rappitest.di.base

import androidx.lifecycle.ViewModel
import com.example.rappitest.di.component.DaggerViewModelInjector
import com.example.rappitest.di.component.ViewModelInjector
import com.example.rappitest.di.module.NetworkModule
import com.example.rappitest.movies.viewModel.ListPopularAndTopMoviesViewModel

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
           is ListPopularAndTopMoviesViewModel -> injector.inject(this)
        }
    }
}