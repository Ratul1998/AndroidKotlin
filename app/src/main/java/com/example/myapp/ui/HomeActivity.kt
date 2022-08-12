package com.example.myapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.R
import com.example.myapp.databinding.ActivityHomeBinding
import com.example.myapp.ui.fragments.MovieFragment
import com.example.myapp.util.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        setUpViewPager(binding.tabViewpager,binding.moviesTabLayout)
        binding.favButton.setOnClickListener(View.OnClickListener {

            startActivity(Intent(this,FavouriteActivity::class.java))
        })

    }

    private fun setUpViewPager(viewPager: ViewPager2, tabLayout: TabLayout){
        val fragments = listOf<Fragment>(MovieFragment(),MovieFragment())
        val adapter = ViewPagerAdapter(this,fragments)
        binding.tabViewpager.adapter = adapter
        TabLayoutMediator(tabLayout,viewPager) {tab,position -> tab.text = if(position == 0) "Trending" else "Now Playing"}.attach()
    }
}