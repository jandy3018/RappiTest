package com.example.rappitest.repository

import com.example.rappitest.movies.data.services.MoviesApiService
import com.example.rappitest.movies.model.ResponseDetailMovie
import com.example.rappitest.movies.model.ResponseLinkVideo
import com.example.rappitest.movies.model.ResponseListMovies
import io.reactivex.Observable
import javax.inject.Inject

class FakeRepository @Inject constructor() : MoviesApiService {
    @Inject
    lateinit var movieApi: MoviesApiService

    var moviesList = ResponseListMovies()
    var moviesListMostPopular = ResponseListMovies()
    var movieDetailMovie = ResponseDetailMovie()
    var dataMovieTrailer = ResponseLinkVideo()


    override  fun getTopRated(): Observable<ResponseListMovies> {
        return Observable.just(moviesList)
    }

    override  fun getMostPopular(): Observable<ResponseListMovies> {
        return  Observable.just(moviesListMostPopular)
    }

    override fun getMovieById(idMovie: Long): Observable<ResponseDetailMovie> {
        return  Observable.just(movieDetailMovie)

    }

    override fun getPlayTrailerByIdMovie(idMovie: String): Observable<ResponseLinkVideo> {
        return  Observable.just(dataMovieTrailer)
    }
}