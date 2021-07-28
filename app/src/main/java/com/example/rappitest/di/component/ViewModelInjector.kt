package com.example.rappitest.di.component

import com.example.rappitest.di.module.NetworkModule
import com.example.rappitest.movies.data.repository.RepositoryMovies
import com.example.rappitest.movies.viewModel.MostPopularViewModel
import com.example.rappitest.movies.viewModel.MoviesListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(moviesListViewModel: MoviesListViewModel)

    fun inject(mostPopularViewModel: MostPopularViewModel)

    fun inject(repo: RepositoryMovies)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}