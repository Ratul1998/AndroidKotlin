package com.example.myapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.LocalDatabase
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieService
import com.example.myapp.repositories.MoviesRepository
import com.example.myapp.ui.adapters.MovieRecyclerViewAdapter
import com.example.myapp.util.HomeViewModelFactory
import com.example.myapp.util.NetworkConnectionInterceptor
import com.example.myapp.viewmodel.HomeViewModel

class MovieFragment : Fragment() , LifecycleObserver {



    private var position = -1

    lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("position") }?.apply {
            position = getInt("position")
        }

        val movieDao = LocalDatabase.getDatabase(requireContext()).movieDao()
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val movieService = MovieService.invoke(networkConnectionInterceptor)
        val movieRepository = MoviesRepository(movieService,requireContext(),movieDao)
        val homeViewModelFactory = HomeViewModelFactory(movieRepository)

        viewModel = ViewModelProvider(requireActivity(),homeViewModelFactory)[HomeViewModel::class.java]


        when(position){
            1 -> {
                viewModel.livePopularMovie.observe(requireActivity(), Observer { movies ->

                    if(movies.isNullOrEmpty()){
                        println("Nothing to show")
                    }else{
                        setRecyclerView(movies,view)
                    }

                })

                viewModel.getPopularMovies()
            }
            2 -> {
                viewModel.liveNowPlayingMovie.observe(requireActivity(), Observer { movies ->

                    if(movies.isNullOrEmpty()){
                        println("Nothing to show")
                    }else{
                        setRecyclerView(movies,view)
                    }

                })

                viewModel.getNowPlaying()
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRecyclerView(movies : List<Movie>, view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = MovieRecyclerViewAdapter(movies,requireContext())

        val layoutManager = GridLayoutManager(requireContext(),3)

        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()

    }



}