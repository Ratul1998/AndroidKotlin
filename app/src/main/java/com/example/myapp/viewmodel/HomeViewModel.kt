package com.example.myapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.user.User
import com.example.myapp.repositories.AuthRepository
import com.example.myapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeViewModel(private  val movieRepository: MoviesRepository) : ViewModel() {

    private var popularMovies : MutableLiveData<ArrayList<Movie>> = MutableLiveData<ArrayList<Movie>>()

    val livePopularMovie : LiveData<ArrayList<Movie>>
     get() = popularMovies

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {

            popularMovies.postValue(movieRepository.getPopularMovies())

        }
    }

}