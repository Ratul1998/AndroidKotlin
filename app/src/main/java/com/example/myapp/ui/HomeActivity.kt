package com.example.myapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.Api
import com.example.myapp.LocalDatabase
import com.example.myapp.R
import com.example.myapp.data.movies.MovieDao
import com.example.myapp.databinding.ActivityHomeBinding
import com.example.myapp.repositories.AuthRepository
import com.example.myapp.repositories.MoviesRepository
import com.example.myapp.util.HomeViewModelFactory
import com.example.myapp.util.ViewPagerAdapter
import com.example.myapp.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)

        setUpViewPager(binding.tabViewpager,binding.moviesTabLayout)

    }

    private fun setUpViewPager(viewPager: ViewPager2, tabLayout: TabLayout){
        val adapter = ViewPagerAdapter(this,2)
        binding.tabViewpager.adapter = adapter
        TabLayoutMediator(tabLayout,viewPager) {tab,position -> tab.text = if(position == 0) "Trending" else "Now Playing"}.attach()
    }
}