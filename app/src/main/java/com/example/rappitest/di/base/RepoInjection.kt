package com.example.rappitest.di.base

import com.example.rappitest.di.component.DaggerViewModelInjector
import com.example.rappitest.di.component.ViewModelInjector
import com.example.rappitest.di.module.NetworkModule
import com.example.rappitest.movies.data.repository.RepositoryMovies

abstract class RepoInjection{
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        injectRepo()
    }

    /**
     * Injects the required dependencies
     */
    private fun injectRepo() {
        when (this) {
            is RepositoryMovies -> injector.inject(this)
        }
    }
}