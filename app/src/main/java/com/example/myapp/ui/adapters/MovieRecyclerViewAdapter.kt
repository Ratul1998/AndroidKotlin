package com.example.myapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.data.movies.Movie
import com.example.myapp.ui.MovieActivity

class MovieRecyclerViewAdapter(private val items:List<Movie>, private val context : Context) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        holder.title.text = movie.vote_average.toString()
        holder.poster.let {
            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .centerCrop()
                .placeholder(R.drawable.ic_app_logo)
                .into(it)
        }

        holder.card.setOnClickListener(View.OnClickListener {
            val intent = Intent(context,MovieActivity::class.java)
            intent.putExtra("id",movie.id)
            context.startActivity(intent)

        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_row, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var title: TextView
        var poster:ImageView
        var card : LinearLayout
        init {
            this.title = row.findViewById(R.id.titleTextView)
            this.poster = row.findViewById(R.id.posterImageView)
            this.card = row.findViewById(R.id.cardLinearLayout)
        }
    }


}