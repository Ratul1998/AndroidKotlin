package com.example.myapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.movies.Movie
import com.example.myapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private  val movieRepository: MoviesRepository) : ViewModel() {

    private var popularMovies : MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()

    val livePopularMovie : LiveData<List<Movie>>
     get() = popularMovies

    private var nowPlayingMovies : MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()

    val liveNowPlayingMovie : LiveData<List<Movie>>
        get() = nowPlayingMovies

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {

            popularMovies.postValue(movieRepository.getPopularMovies())

        }
    }

    fun getNowPlaying(){
        viewModelScope.launch(Dispatchers.IO) {

            nowPlayingMovies.postValue(movieRepository.getNowPlayingMovies())

        }
    }

}