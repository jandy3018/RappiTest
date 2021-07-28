package com.example.rappitest.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseDetailMovie {
    @SerializedName("adult")
    @Expose
    var isAdult = false

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null

    @SerializedName("homepage")
    @Expose
    var homepage: String? = null

    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity = 0.0

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("revenue")
    @Expose
    var revenue: Long = 0

    @SerializedName("runtime")
    @Expose
    var runtime: Long = 0

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("tagline")
    @Expose
    var tagline: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("video")
    @Expose
    var isVideo = false

    @SerializedName("vote_average")
    @Expose
    var voteAverage = 0.0

    @SerializedName("vote_count")
    @Expose
    var voteCount: Long = 0
    fun withAdult(adult: Boolean): ResponseDetailMovie {
        isAdult = adult
        return this
    }

    fun withBackdropPath(backdropPath: String?): ResponseDetailMovie {
        this.backdropPath = backdropPath
        return this
    }

    fun withGenres(genres: List<Genre>?): ResponseDetailMovie {
        this.genres = genres
        return this
    }

    fun withHomepage(homepage: String?): ResponseDetailMovie {
        this.homepage = homepage
        return this
    }

    fun withId(id: Long): ResponseDetailMovie {
        this.id = id
        return this
    }

    fun withImdbId(imdbId: String?): ResponseDetailMovie {
        this.imdbId = imdbId
        return this
    }

    fun withOriginalLanguage(originalLanguage: String?): ResponseDetailMovie {
        this.originalLanguage = originalLanguage
        return this
    }

    fun withOriginalTitle(originalTitle: String?): ResponseDetailMovie {
        this.originalTitle = originalTitle
        return this
    }

    fun withOverview(overview: String?): ResponseDetailMovie {
        this.overview = overview
        return this
    }

    fun withPopularity(popularity: Double): ResponseDetailMovie {
        this.popularity = popularity
        return this
    }

    fun withPosterPath(posterPath: String?): ResponseDetailMovie {
        this.posterPath = posterPath
        return this
    }

    fun withReleaseDate(releaseDate: String?): ResponseDetailMovie {
        this.releaseDate = releaseDate
        return this
    }

    fun withRevenue(revenue: Long): ResponseDetailMovie {
        this.revenue = revenue
        return this
    }

    fun withRuntime(runtime: Long): ResponseDetailMovie {
        this.runtime = runtime
        return this
    }

    fun withStatus(status: String?): ResponseDetailMovie {
        this.status = status
        return this
    }

    fun withTagline(tagline: String?): ResponseDetailMovie {
        this.tagline = tagline
        return this
    }

    fun withTitle(title: String?): ResponseDetailMovie {
        this.title = title
        return this
    }

    fun withVideo(video: Boolean): ResponseDetailMovie {
        isVideo = video
        return this
    }

    fun withVoteAverage(voteAverage: Double): ResponseDetailMovie {
        this.voteAverage = voteAverage
        return this
    }

    fun withVoteCount(voteCount: Long): ResponseDetailMovie {
        this.voteCount = voteCount
        return this
    }
}