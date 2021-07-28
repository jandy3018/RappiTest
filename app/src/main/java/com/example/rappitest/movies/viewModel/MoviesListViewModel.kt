package com.example.rappitest.movies.viewModel

import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.example.rappitest.R
import com.example.rappitest.database.MovieDao
import com.example.rappitest.di.base.BaseViewModel
import com.example.rappitest.movies.adapter.MoviesAdapter
import com.example.rappitest.movies.adapter.MoviesTopRatedAdapter
import com.example.rappitest.movies.data.repository.RepositoryMovies
import com.example.rappitest.movies.model.ClsMovie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesListViewModel (private val movieDao: MovieDao?) : BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val movieTopRatedAdapter: MoviesTopRatedAdapter = MoviesTopRatedAdapter(this)
    val movieMostPopularAdapter: MoviesAdapter = MoviesAdapter(this)
    val tvTitleToolbar: MutableLiveData<TextView> = MutableLiveData()
    val tvHeaderTopRated: MutableLiveData<TextView> = MutableLiveData()
    val tvHeaderMostPopular: MutableLiveData<TextView> = MutableLiveData()
    val tvNoFound: MutableLiveData<TextView> = MutableLiveData()

    val errorClickListener = View.OnClickListener { getListMoviesTopRated() }
    private lateinit var subscription: Disposable

    init {
        getListMoviesTopRated()
    }

    fun getListMoviesTopRated() {
        if (movieDao == null) return
        subscription =
            RepositoryMovies().getListMoviesTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onShowLoading() }
                .subscribe(
                    { movies ->
                        Observable.fromCallable {
                            movieDao.deleteTopRated()
                            movies.results!!.forEach { it.withIdTable(1) }
                            movieDao.insertAll(* movies.results!!.toTypedArray())
                        }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnTerminate { getListMoviesMostPopular() }
                            .doOnError { getListMoviesMostPopular() }
                            .subscribe {
                                onRetrieveTopRatedListSuccess(movies!!.results!!)
                            }
                    },
                    {
                        Observable.fromCallable { movieDao.all }
                            .concatMap { moviesBD ->
                                if (moviesBD.isEmpty())
                                    Observable.just(listOf(ClsMovie()))
                                else
                                    Observable.just(moviesBD)
                            }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnTerminate { getListMoviesMostPopular() }
                            .doOnError { getListMoviesMostPopular() }
                            .subscribe { moviesConnectionError ->
                                if (moviesConnectionError.size == 1)
                                    onShowConnectionError(R.string.request_connection_error_empty_bd)
                                else {
                                    onShowConnectionError(R.string.request_connection_error)
                                    onRetrieveTopRatedListSuccess(moviesConnectionError)
                                }
                            }
                    }
                )
    }

    fun getListMoviesMostPopular() {
        subscription = RepositoryMovies().getListMoviesMostPopular()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    (tvTitleToolbar.value as TextView).text = tvTitleToolbar.value!!.context.getString( R.string.app_name)
                    Observable.fromCallable {
                        movieDao?.deleteMostPopular()
                        result.results!!.forEach { it.withIdTable(2) }
                        movieDao?.insertAll(* result.results!!.toTypedArray())
                    }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate { onHideLoading() }
                        .doOnError { onHideLoading() }
                        .subscribe {
                            onRetrieveMostPopularListSuccess(result.results!!)
                        }

                },
                {
                    Observable.fromCallable { movieDao?.allMostPopular }
                        .concatMap { moviesBD ->
                            (tvTitleToolbar.value as TextView).text = tvTitleToolbar.value!!.context.getString( R.string.title_toolbar_offline)
                            if (moviesBD.isEmpty())
                                Observable.just(listOf(ClsMovie()))
                            else
                                Observable.just(moviesBD)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate { onHideLoading() }
                        .doOnError { onHideLoading() }
                        .subscribe { resultConnectionError ->
                            if (resultConnectionError.size == 1)
                                onShowConnectionError(R.string.request_connection_error_empty_bd)
                            else {
                                onShowConnectionError(R.string.request_connection_error)
                                onRetrieveMostPopularListSuccess(resultConnectionError)
                            }
                        }
                }
            )

    }

    private fun onShowLoading() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onHideLoading() {
        loadingVisibility.value = View.GONE
    }

    fun onRetrieveTopRatedListSuccess(results: List<ClsMovie>) {
        movieTopRatedAdapter.updatePostList(results)
    }

    private fun onRetrieveMostPopularListSuccess(results: List<ClsMovie>) {
        movieMostPopularAdapter.updatePostList(results)
    }

    private fun onShowConnectionError(idError: Int) {
        errorMessage.value = idError
    }
}