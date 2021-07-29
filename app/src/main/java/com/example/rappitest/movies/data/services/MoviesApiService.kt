package com.example.rappitest.movies.data.services

import com.example.rappitest.movies.model.ResponseDetailMovie
import com.example.rappitest.movies.model.ResponseLinkVideo
import com.example.rappitest.movies.model.ResponseListMovies
import com.example.rappitest.utils.API_KEY_TMDB
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {

    @GET("popular?api_key=$API_KEY_TMDB&language=en-US&page=1")
    fun getMostPopular(): Observable<ResponseListMovies>

    @GET("top_rated?language=en-US&page=undefined&api_key=$API_KEY_TMDB")
    fun getTopRated(): Observable<ResponseListMovies>

    @GET("{id}?api_key=$API_KEY_TMDB&language=en-US")
    fun getMovieById(@Path(value="id", encoded=false) idMovie:Long): Observable<ResponseDetailMovie>

    @GET("{id}/videos?api_key=$API_KEY_TMDB&language=en-US")
    fun getPlayTrailerByIdMovie(@Path(value="id", encoded=false) idMovie:String): Observable<ResponseLinkVideo>

}