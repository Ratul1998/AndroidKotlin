package com.example.myapp.data.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavMovie(movie: FavMovie)

    @Query("SELECT * FROM movie_table WHERE id=:id")
    suspend fun getMovieById(id : Int) : List<Movie>

    @Query("SELECT * FROM movie_table")
    suspend fun getMovies() : List<Movie>

    @Query("SELECT * FROM movie_table WHERE EXISTS (SELECT movieId FROM fav_movies WHERE movieId=movie_table.id)")
    suspend fun getFavouriteMovies() : List<Movie>


}