package com.example.myapp.repositories

import android.content.Context
import com.example.myapp.R
import com.example.myapp.data.movies.FavMovie
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieDao
import com.example.myapp.data.movies.MovieService
import com.example.myapp.util.SafeApiRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieService: MovieService, @ApplicationContext private val context: Context, private val movieDao: MovieDao) : SafeApiRequest() {

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

    suspend fun addFavMovie(movie : FavMovie){
        movieDao.addFavMovie(movie)
    }

    suspend fun getFavMovies() : List<Movie>{
        return movieDao.getFavouriteMovies()
    }

}

