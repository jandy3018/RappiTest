package com.example.rappitest.di.component

import com.example.rappitest.di.module.NetworkModule
import com.example.rappitest.movies.viewModel.ListPopularAndTopMoviesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(listPopularAndTopMoviesViewModel: ListPopularAndTopMoviesViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}