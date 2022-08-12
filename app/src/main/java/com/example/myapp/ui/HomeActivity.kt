package com.example.myapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.R
import com.example.myapp.databinding.ActivityHomeBinding
import com.example.myapp.ui.fragments.MovieFragment
import com.example.myapp.util.ViewPagerAdapter
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
        val fragments = listOf<Fragment>(MovieFragment(),MovieFragment())
        val adapter = ViewPagerAdapter(this,fragments)
        binding.tabViewpager.adapter = adapter
        TabLayoutMediator(tabLayout,viewPager) {tab,position -> tab.text = if(position == 0) "Trending" else "Now Playing"}.attach()
    }
}