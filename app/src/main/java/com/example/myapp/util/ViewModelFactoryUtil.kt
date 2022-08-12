package com.example.myapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.repositories.AuthRepository
import com.example.myapp.repositories.MoviesRepository

class AuthViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AuthRepository :: class.java).newInstance(authRepository)
    }

}

class HomeViewModelFactory(private val movieRepository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepository :: class.java).newInstance(movieRepository)
    }

}

class MovieViewModelFactory(private val movieRepository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepository :: class.java).newInstance(movieRepository)
    }

}

class FavMovieViewModelFactory(private val movieRepository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepository :: class.java).newInstance(movieRepository)
    }

}