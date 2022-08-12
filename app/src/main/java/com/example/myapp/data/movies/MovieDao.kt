package com.example.myapp.data.movies

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDao {

    @GET(value = "movie/popular")
    suspend fun<T : Any>  getPopularMovies(@Query("api_key") api_key :String) : Response<T>

    @GET(value = "movie/now_playing")
    suspend fun<T : Any>  getNowPlayingMovies(@Query("api_key") api_key :String) : Response<T>

    companion object{
        operator fun invoke(): MovieDao {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieDao::class.java)
        }
    }

}