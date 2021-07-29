package com.example.rappitest.movies.data.repository

import com.example.rappitest.di.base.RepoInjection
import com.example.rappitest.movies.data.services.MoviesApiService
import com.example.rappitest.movies.model.ResponseDetailMovie
import com.example.rappitest.movies.model.ResponseLinkVideo
import com.example.rappitest.movies.model.ResponseListMovies
import dagger.Module
import io.reactivex.Observable
import javax.inject.Inject

@Module
class RepositoryMovies : RepoInjection() {
    @Inject
    lateinit var movieApi: MoviesApiService

    fun getListMoviesTopRated(): Observable<ResponseListMovies> {
        return movieApi.getMostPopular()
    }

    fun getListMoviesMostPopular(): Observable<ResponseListMovies> {
        return movieApi.getTopRated()
    }

    fun getMovieById(idMovie:Long): Observable<ResponseDetailMovie> {
        return movieApi.getMovieById(idMovie)
    }

    fun getPlayTrailerByIdMovie(idMovie:String): Observable<ResponseLinkVideo> {
        return movieApi.getPlayTrailerByIdMovie(idMovie)
    }
}