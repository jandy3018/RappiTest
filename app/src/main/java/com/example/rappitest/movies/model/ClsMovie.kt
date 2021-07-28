package com.example.rappitest.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
open class ClsMovie {
    @SerializedName("adult")
    @Expose
    var isAdult = false

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("id")
    @Expose
    var id: Long = 0

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

    var idTable: Long = 0

    fun withAdult(adult: Boolean): ClsMovie {
        isAdult = adult
        return this
    }

    fun withBackdropPath(backdropPath: String?): ClsMovie {
        this.backdropPath = backdropPath
        return this
    }

    fun withId(id: Long): ClsMovie {
        this.id = id
        return this
    }

    fun withOriginalLanguage(originalLanguage: String?): ClsMovie {
        this.originalLanguage = originalLanguage
        return this
    }

    fun withOriginalTitle(originalTitle: String?): ClsMovie {
        this.originalTitle = originalTitle
        return this
    }

    fun withOverview(overview: String?): ClsMovie {
        this.overview = overview
        return this
    }

    fun withPopularity(popularity: Double): ClsMovie {
        this.popularity = popularity
        return this
    }

    fun withPosterPath(posterPath: String?): ClsMovie {
        this.posterPath = posterPath
        return this
    }

    fun withReleaseDate(releaseDate: String?): ClsMovie {
        this.releaseDate = releaseDate
        return this
    }

    fun withTitle(title: String?): ClsMovie {
        this.title = title
        return this
    }

    fun withVideo(video: Boolean): ClsMovie {
        isVideo = video
        return this
    }

    fun withVoteAverage(voteAverage: Double): ClsMovie {
        this.voteAverage = voteAverage
        return this
    }

    fun withVoteCount(voteCount: Long): ClsMovie {
        this.voteCount = voteCount
        return this
    }

    fun withIdTable(idTable: Long): ClsMovie {
        this.idTable = idTable
        return this
    }



    @PrimaryKey(autoGenerate = true)
    var idKey: Int = 0 // or foodId: Int? = null

}