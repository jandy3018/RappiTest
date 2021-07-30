package com.example.rappitest.repository

import com.example.rappitest.movies.model.ClsMovie
import com.example.rappitest.movies.model.ResponseDetailMovie
import com.example.rappitest.movies.model.ResponseLinkVideo
import com.example.rappitest.movies.model.ResponseListMovies
import io.reactivex.observers.TestObserver
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class RepositoryTestFake {

    @Mock
    var fakeRepository: FakeRepository? = null
    private lateinit var testObserver: TestObserver<ResponseListMovies>
    private lateinit var testObserverDetail: TestObserver<ResponseDetailMovie>

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        testObserver= TestObserver()
        testObserverDetail= TestObserver()
    }

    @Test
    fun getTopRated() {
        // Given
        fakeRepository!!.moviesList = ResponseListMovies().withResults(listOf(ClsMovie().withPosterPath("path1")))
        // When
        fakeRepository!!.getTopRated().subscribe(testObserver)
        // Then
        testObserver.assertComplete()
        testObserver.assertValue{it.results!!.isNotEmpty()}
    }

    @Test
    fun getMostPopular() {
        // Given
        fakeRepository!!.moviesListMostPopular = ResponseListMovies().withResults(null)
        // When
        fakeRepository!!.getTopRated().subscribe(testObserver)
        // Then
        testObserver.assertComplete()
        testObserver.assertValue{it.results!!.isNotEmpty()}
    }

    @Test
    fun getMovieById() {
        // Given
        val initialRequest= fakeRepository!!.getMovieById(0)
        fakeRepository!!.movieDetailMovie = ResponseDetailMovie().withPosterPath(null)
        // When
        val getMovieById = runBlocking { fakeRepository!!.getMovieById(0) }
        // Then
        Assert.assertTrue(initialRequest != getMovieById)
    }

    @Test
    fun getPlayTrailerByIdMovie() {
        // Given
        val initialRequest= fakeRepository!!.getPlayTrailerByIdMovie("1234")
        fakeRepository!!.dataMovieTrailer = ResponseLinkVideo().withResults(null)
        // When
        val getDataTrailerMovieById = runBlocking { fakeRepository!!.getPlayTrailerByIdMovie("1232") }
        // Then
        Assert.assertTrue(initialRequest != getDataTrailerMovieById)
    }
}