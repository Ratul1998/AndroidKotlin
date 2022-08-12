package com.example.myapp.data.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Query("SELECT * FROM movie_table WHERE id=:id")
    suspend fun getMovieById(id : Int) : List<Movie>

    @Query("SELECT * FROM movie_table")
    suspend fun getMovies() : List<Movie>


}