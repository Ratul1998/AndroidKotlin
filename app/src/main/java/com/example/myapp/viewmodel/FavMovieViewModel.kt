package com.example.myapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.movies.Movie
import com.example.myapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavMovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var favMovies : MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()

    val liveFavMovie : LiveData<List<Movie>>
        get() = favMovies

    fun getFavMovies() {

        viewModelScope.launch(Dispatchers.IO){

            favMovies.postValue(moviesRepository.getFavMovies())

        }

    }

}