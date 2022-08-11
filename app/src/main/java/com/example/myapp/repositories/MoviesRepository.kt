package com.example.myapp.repositories

import android.content.Context
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieDao
import com.example.myapp.util.SafeApiRequest
import java.util.ArrayList

class MoviesRepository(private val movieDao: MovieDao ,private val context: Context) : SafeApiRequest() {

    suspend fun getPopularMovies(): ArrayList<Movie> {
        val response =
            apiRequest { movieDao.getPopularMovies(context.applicationContext.getString(R.string.api_key)) }
        return (response as Map<*, *>)["results"] as ArrayList<Movie>
    }

    suspend fun getNowPlayingMovies() {
        return apiRequest { movieDao.getNowPlayingMovies(context.applicationContext.getString(R.string.api_key))}
    }

}

