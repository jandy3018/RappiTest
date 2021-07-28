package com.example.rappitest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rappitest.movies.model.ClsMovie


@Dao
interface MovieDao {

    @get:Query("SELECT * FROM ClsMovie WHERE idTable=1")
    val all: List<ClsMovie>

    @Insert
    fun insertAll(vararg movies: ClsMovie)

    @Query("DELETE FROM ClsMovie WHERE idTable = 1")
    fun deleteTopRated()

    @get:Query("SELECT * FROM ClsMovie WHERE idTable=2")
    val allMostPopular: List<ClsMovie>

    @Insert
    fun insertAllMostPopular(vararg moviesMostPopular: ClsMovie)

    @Query("DELETE FROM ClsMovie WHERE idTable=2")
    fun deleteMostPopular()

}