package com.example.myapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.movies.Movie
import com.example.myapp.repositories.MoviesRepository
import com.example.myapp.util.NoNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private  val movieRepository: MoviesRepository) : ViewModel() {

    private var popularMovies : MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()

    val livePopularMovie : LiveData<List<Movie>>
     get() = popularMovies

    private var nowPlayingMovies : MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()

    val liveNowPlayingMovie : LiveData<List<Movie>>
        get() = nowPlayingMovies

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val list = movieRepository.getPopularMovies()
                popularMovies.postValue(list)
                list.forEach{ movie ->
                    movieRepository.addMovie(movie)
                }
            }
            catch (e:NoNetworkException){
                popularMovies.postValue(movieRepository.getMovies())
            }


        }
    }

    fun getNowPlaying(){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                nowPlayingMovies.postValue(movieRepository.getNowPlayingMovies())
            }
            catch (e:NoNetworkException){
                println(e.message)
            }


        }
    }

}