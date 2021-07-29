package com.example.rappitest.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultLinkTrailer {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null

    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("site")
    @Expose
    var site: String? = null

    @SerializedName("size")
    @Expose
    var size: Long = 0

    @SerializedName("type")
    @Expose
    var type: String? = null
    fun withId(id: String?): ResultLinkTrailer {
        this.id = id
        return this
    }

    fun withIso6391(iso6391: String?): ResultLinkTrailer {
        this.iso6391 = iso6391
        return this
    }

    fun withIso31661(iso31661: String?): ResultLinkTrailer {
        this.iso31661 = iso31661
        return this
    }

    fun withKey(key: String?): ResultLinkTrailer {
        this.key = key
        return this
    }

    fun withName(name: String?): ResultLinkTrailer {
        this.name = name
        return this
    }

    fun withSite(site: String?): ResultLinkTrailer {
        this.site = site
        return this
    }

    fun withSize(size: Long): ResultLinkTrailer {
        this.size = size
        return this
    }

    fun withType(type: String?): ResultLinkTrailer {
        this.type = type
        return this
    }
}