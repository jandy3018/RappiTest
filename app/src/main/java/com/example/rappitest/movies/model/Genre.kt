package com.example.rappitest.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Genre {
    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("name")
    @Expose
    var name: String? = null
    fun withId(id: Long): Genre {
        this.id = id
        return this
    }

    fun withName(name: String?): Genre {
        this.name = name
        return this
    }
}