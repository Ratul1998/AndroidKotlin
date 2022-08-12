package com.example.myapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.movies.Movie
import com.example.myapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository : MoviesRepository) : ViewModel(){

    private var movie : MutableLiveData<Movie> = MutableLiveData<Movie>()

    val liveMovie : LiveData<Movie>
        get() = movie

    fun getMovie(id : Int){

        viewModelScope.launch(Dispatchers.IO) {

            movie.postValue(movieRepository.getMovie(id))

        }

    }
}