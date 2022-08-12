package com.example.myapp.data.movies

import com.example.myapp.util.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(value = "movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key :String) : Response<MovieResponse>

    @GET(value = "movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") api_key :String) : Response<MovieResponse>

    @GET(value = "movie/{id}")
    suspend fun getMovie(@Path("id") id:String, @Query("api_key") api_key :String) : Response<Movie>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MovieService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()


            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }

}