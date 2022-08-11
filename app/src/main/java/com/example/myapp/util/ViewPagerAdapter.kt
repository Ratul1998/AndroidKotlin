package com.example.myapp.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapp.ui.fragments.MovieFragment

class ViewPagerAdapter(fragment : FragmentActivity,private  val count:Int) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment {
        val fragment = MovieFragment()
        fragment.arguments = Bundle().apply {
            putInt("position", position + 1)
        }
        return fragment
    }


}