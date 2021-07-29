package com.example.rappitest.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseLinkVideo {
    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("results")
    @Expose
    var results: List<ResultLinkTrailer>? = null
    fun withId(id: Long): ResponseLinkVideo {
        this.id = id
        return this
    }

    fun withResults(results: List<ResultLinkTrailer>?): ResponseLinkVideo {
        this.results = results
        return this
    }
}