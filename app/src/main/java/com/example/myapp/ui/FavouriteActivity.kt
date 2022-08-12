package com.example.myapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.databinding.ActivityFavouriteBinding
import com.example.myapp.ui.adapters.FavMovieRecyclerViewAdapter
import com.example.myapp.viewmodel.FavMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity() {

    private val viewModel : FavMovieViewModel by viewModels()
    private lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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