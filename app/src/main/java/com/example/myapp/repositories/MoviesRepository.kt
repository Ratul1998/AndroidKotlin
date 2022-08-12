package com.example.myapp.repositories

import android.content.Context
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieDao
import com.example.myapp.util.SafeApiRequest

class MoviesRepository(private val movieDao: MovieDao ,private val context: Context) : SafeApiRequest() {

    suspend fun getPopularMovies(): List<Movie> {
        val response =
            apiRequest { movieDao.getPopularMovies(context.applicationContext.getString(R.string.api_key)) }
        return response.results.toList()
    }

    suspend fun getNowPlayingMovies(): List<Movie> {
        val response = apiRequest { movieDao.getNowPlayingMovies(context.applicationContext.getString(R.string.api_key))}
        return response.results.toList()
    }

    suspend fun getMovie(id:Int) : Movie {
        return apiRequest { movieDao.getMovie(id.toString(),context.applicationContext.getString(R.string.api_key)) }
    }

}

