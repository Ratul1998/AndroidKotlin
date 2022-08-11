package com.example.myapp

import com.example.myapp.data.movies.MovieDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Api {


    fun getMovieDao() : MovieDao



}