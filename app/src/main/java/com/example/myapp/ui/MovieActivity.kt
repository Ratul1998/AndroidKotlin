package com.example.myapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapp.LocalDatabase
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieService
import com.example.myapp.databinding.ActivityMovieBinding
import com.example.myapp.repositories.MoviesRepository
import com.example.myapp.util.MovieViewModelFactory
import com.example.myapp.util.NetworkConnectionInterceptor
import com.example.myapp.viewmodel.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var viewModel : MovieViewModel
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val id = intent.getIntExtra("id",-1)

        val movieDao = LocalDatabase.getDatabase(this).movieDao()
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val movieService = MovieService.invoke(networkConnectionInterceptor)
        val movieRepository  = MoviesRepository(movieService, this,movieDao)
        val movieViewModelFactory  = MovieViewModelFactory(movieRepository)

        viewModel = ViewModelProvider(this, movieViewModelFactory)[MovieViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        viewModel.liveMovie.observe(this, Observer{ movie-> setUpPage(movie) })

        if(id!=-1)
            viewModel.getMovie(id)

        binding.favButtonImageView.setOnClickListener(View.OnClickListener {
            viewModel.addToFavourites(id,this)
        })

    }

    private fun setUpPage(movie: Movie) {

        binding.movieNameTextView.text = movie.title
        binding.moviePosterImageView.let {
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .centerCrop()
                .placeholder(R.drawable.ic_app_logo)
                .into(it)
        }

        binding.movieOverViewTextView.text = movie.overview

    }
}