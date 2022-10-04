package com.globe.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.globe.drawer.R

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: ArrayList<Movie> = ArrayList()
    var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("MovieAdapter", "Position:$position")
        val movie : Movie = movies.get(position)

        holder.tvMovieTitle.text = movie.title
        holder.rbMovieRating.rating = movie.rating
        holder.ivMoviePoster.setImageResource(movie.poster)
        holder.tvDate.text = movie.date.toString()

        holder.ivMoviePoster.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {

                onItemClickListener?.onItemClicked(holder.adapterPosition)
            }
        })
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(itemView: View) : ViewHolder(itemView){
        val ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
        val tvMovieTitle: TextView = itemView.findViewById(R.id.tvDMovieTitle)
        val rbMovieRating: RatingBar = itemView.findViewById(R.id.rbDMovieRating)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }
}