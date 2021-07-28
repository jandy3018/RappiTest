package com.example.rappitest.movies.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class ResponseListMovies {
    @SerializedName("page")
    @Expose
    var page: Long = 0

    @SerializedName("results")
    @Expose
    var results: List<MovieObject>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Long = 0

    @SerializedName("total_results")
    @Expose
    var totalResults: Long = 0
    fun withPage(page: Long): ResponseListMovies {
        this.page = page
        return this
    }

    fun withResults(results: List<MovieObject>?): ResponseListMovies {
        this.results = results
        return this
    }

    fun withTotalPages(totalPages: Long): ResponseListMovies {
        this.totalPages = totalPages
        return this
    }

    fun withTotalResults(totalResults: Long): ResponseListMovies {
        this.totalResults = totalResults
        return this
    }
}