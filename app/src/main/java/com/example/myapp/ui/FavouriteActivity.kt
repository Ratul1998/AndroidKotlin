package com.example.myapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.LocalDatabase
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieService
import com.example.myapp.databinding.ActivityFavouriteBinding
import com.example.myapp.repositories.MoviesRepository
import com.example.myapp.ui.adapters.FavMovieRecyclerViewAdapter
import com.example.myapp.util.FavMovieViewModelFactory
import com.example.myapp.util.NetworkConnectionInterceptor
import com.example.myapp.viewmodel.FavMovieViewModel

class FavouriteActivity : AppCompatActivity() {

    private lateinit var viewModel : FavMovieViewModel
    private lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieDao = LocalDatabase.getDatabase(this).movieDao()
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val movieService = MovieService.invoke(networkConnectionInterceptor)
        val movieRepository  = MoviesRepository(movieService, this,movieDao)
        val favMovieViewModelFactory  = FavMovieViewModelFactory(movieRepository)

        viewModel = ViewModelProvider(this, favMovieViewModelFactory)[FavMovieViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite)

        viewModel.liveFavMovie.observe(this, Observer { movies ->
            setRecyclerView(movies)
        })

        viewModel.getFavMovies()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRecyclerView(movies : List<Movie>) {

        val recyclerView = binding.favMoviesRecyclerView

        val adapter = FavMovieRecyclerViewAdapter(movies,this)

        val layoutManager = GridLayoutManager(this,3)

        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()

    }
}