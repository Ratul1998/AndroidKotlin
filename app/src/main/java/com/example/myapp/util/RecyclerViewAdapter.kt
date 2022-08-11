package com.example.myapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import java.util.ArrayList

class RecyclerViewAdapter(private val items:List<Movie>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position] as Movie
        holder.title?.text  = movie.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_row, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var title: TextView? = null

        init {
            this.title = row.findViewById<TextView>(R.id.titleTextView)
        }
    }


}