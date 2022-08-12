package com.example.myapp.di

import android.content.Context
import com.example.myapp.LocalDatabase
import com.example.myapp.data.movies.MovieDao
import com.example.myapp.data.movies.MovieService
import com.example.myapp.data.user.UserDao
import com.example.myapp.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideMovieService(interceptor: NetworkConnectionInterceptor) : MovieService{
        return MovieService.invoke(interceptor)
    }

    @Provides
    fun provideMovieDao(@ApplicationContext context: Context) : MovieDao {
        return LocalDatabase.getDatabase(context).movieDao()
    }

    @Provides
    fun provideUserDao(@ApplicationContext context: Context) : UserDao {
        return LocalDatabase.getDatabase(context).userDao()
    }


}