package com.example.myapp.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.movies.FavMovie
import com.example.myapp.data.movies.Movie
import com.example.myapp.repositories.MoviesRepository
import com.example.myapp.util.NoNetworkException
import com.example.myapp.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository : MoviesRepository) : ViewModel(){

    private var movie : MutableLiveData<Movie> = MutableLiveData<Movie>()

    val liveMovie : LiveData<Movie>
        get() = movie

    fun getMovie(id : Int){

        viewModelScope.launch(Dispatchers.IO) {

            try{
                movie.postValue(movieRepository.getMovie(id))
            }
            catch (e : NoNetworkException){
                movie.postValue(movieRepository.getMovieById(id))
            }


        }

    }

    fun addToFavourites (movieId :Int,activity: Activity) {
        viewModelScope.launch(Dispatchers.IO) {

            movieRepository.addFavMovie(FavMovie(0,movieId))

            activity.runOnUiThread {
                activity.applicationContext.toast("Movie Added To Favourites")
            }

        }
    }
}