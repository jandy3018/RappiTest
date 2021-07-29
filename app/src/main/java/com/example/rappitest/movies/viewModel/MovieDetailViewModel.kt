package com.example.rappitest.movies.viewModel

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.rappitest.R
import com.example.rappitest.di.base.BaseViewModel
import com.example.rappitest.movies.adapter.GenreAdapter
import com.example.rappitest.movies.data.repository.RepositoryMovies
import com.example.rappitest.movies.model.Genre
import com.example.rappitest.movies.model.ResponseDetailMovie
import com.example.rappitest.movies.view.PlayTrailerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailViewModel : BaseViewModel(), View.OnClickListener {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val showPlayTrailer: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private val posterPath = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val releaseDate = MutableLiveData<String>()

    private val overview = MutableLiveData<String>()
    private val genre = MutableLiveData<String>()
    private val imdbId = MutableLiveData<String>()

    val genreAdapter: GenreAdapter = GenreAdapter()
    private lateinit var subscription: Disposable
    private var idMovie: Long = 0

    val errorClickListener = View.OnClickListener { loadDetailById() }

    val imageView: MutableLiveData<ImageView> = MutableLiveData()

    fun loadDetailById(
        idMovie: Long,
        posterPath: String,
        title: String,
        releaseDate: String,
        overview: String
    ) {
        Glide.with(imageView.value!!.context)
            .load("https://image.tmdb.org/t/p/w500/$posterPath")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView.value!!)
        this.posterPath.value = posterPath
        this.title.value = title
        if (releaseDate.isNotEmpty())
            this.releaseDate.value = SimpleDateFormat("MMM dd, yyyy", Locale("en", "EN")).format(
                SimpleDateFormat("yyyy-mm-dd", Locale("en", "EN")).parse(
                    releaseDate
                )
            )
        this.overview.value = overview
        this.idMovie = idMovie
        loadDetailById()
    }

    private fun loadDetailById() {
        onHidePlayTrailer()
        subscription = RepositoryMovies().getMovieById(idMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onShowLoading() }
            .doOnTerminate { onHideLoading() }
            .doOnError { onHideLoading() }
            .subscribe(
                { result ->
                    imdbId.value = result.imdbId!!
                    setData(result)
                },
                {
                    onShowConnectionError()
                }
            )
    }

    private fun getPlayTrailerByIdMovie(idMovie: String) {
        subscription = RepositoryMovies().getPlayTrailerByIdMovie(idMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onShowLoading() }
            .doOnTerminate { onHideLoading() }
            .doOnError {
                onHideLoading()
            }
            .subscribe(
                { result ->
                    if (result.results!!.isNotEmpty()) {
                        val context: FragmentActivity =
                            imageView.value!!.context as FragmentActivity
                        val intent =
                            Intent(context, PlayTrailerActivity::class.java)
                        intent.putExtra("key", result.results!![0].key)
                        context.startActivity(
                            intent
                        )
                    } else
                        Toast.makeText(
                            imageView.value!!.context,
                            "No trailer for this movie",
                            Toast.LENGTH_SHORT
                        ).show()
                },
                {
                    onShowConnectionError()
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

    private fun onShowPlayTrailer() {
        showPlayTrailer.value = View.VISIBLE
    }

    private fun onHidePlayTrailer() {
        showPlayTrailer.value = View.GONE
    }

    private fun onShowConnectionError() {
        errorMessage.value = R.string.request_connection_error
    }

    private fun setData(responseDetailMovie: ResponseDetailMovie) {
        bind(responseDetailMovie)

        genreAdapter.updatePostList(responseDetailMovie.genres!!)
    }

    fun bind(result: ResponseDetailMovie) {
        releaseDate.value =
            releaseDate.value + " - " + (result.runtime / 60).toInt() + "h " + (result.runtime % 60) + "m"
        if (result.imdbId!!.isNotEmpty())
            onShowPlayTrailer()
    }

    fun bind(genre: Genre) {
        this.genre.value = genre.name
    }


    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getReleaseDate(): MutableLiveData<String> {
        return releaseDate
    }

    fun getOverview(): MutableLiveData<String> {
        return overview
    }

    fun getGenre(): MutableLiveData<String> {
        return genre
    }

    override fun onClick(v: View?) {
        getPlayTrailerByIdMovie(imdbId.value!!)
    }
}