package com.example.myapp.repositories

import android.content.Context
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieDao
import com.example.myapp.data.movies.MovieService
import com.example.myapp.util.SafeApiRequest

class MoviesRepository(private val movieService: MovieService, private val context: Context, private val movieDao: MovieDao) : SafeApiRequest() {

    suspend fun getPopularMovies(): List<Movie> {
        val response =
            apiRequest { movieService.getPopularMovies(context.applicationContext.getString(R.string.api_key)) }
        return response.results.toList()
    }

    suspend fun getNowPlayingMovies(): List<Movie> {
        val response = apiRequest { movieService.getNowPlayingMovies(context.applicationContext.getString(R.string.api_key))}
        return response.results.toList()
    }

    suspend fun getMovie(id:Int) : Movie {
        return apiRequest { movieService.getMovie(id.toString(),context.applicationContext.getString(R.string.api_key)) }
    }

    suspend fun addMovie(movie: Movie) {
        movieDao.addMovie(movie)
    }

    suspend fun getMovies() : List<Movie> {
        return movieDao.getMovies()
    }

    suspend fun getMovieById(id:Int) : Movie {
        return movieDao.getMovieById(id)[0]
    }

}

