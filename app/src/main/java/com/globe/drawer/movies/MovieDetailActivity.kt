package com.globe.movies

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.globe.drawer.R

class MovieDetailActivity: AppCompatActivity() {

    private lateinit var ivDMoviePoster: ImageView
    private lateinit var tvDMovieTitle: TextView
    private lateinit var rbDMovieRating: RatingBar
    private lateinit var tvPlot: TextView
    private lateinit var tvCast: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        ivDMoviePoster = findViewById(R.id.ivDMoviePoster)
        tvDMovieTitle = findViewById(R.id.tvDMovieTitle)
        rbDMovieRating = findViewById(R.id.rbDMovieRating)
        tvPlot = findViewById(R.id.tvPlot)
        tvCast = findViewById(R.id.tvCast)


        val title = intent.getStringExtra(Constants.TITLE)
        val poster = intent.getIntExtra(Constants.POSTER,0)
        val plot = intent.getStringExtra(Constants.PLOT)
        val rating = intent.getFloatExtra(Constants.RATING,0.0F)
        val cast = intent.getStringExtra(Constants.CAST)

        //val movie : Movie = intent.getSerializableExtra(Constants.MOVIE) as Movie
        //tvDMovieTitle.text = movie.title
        //tvPlot.text = movie.plot
        //ivDMoviePoster.setImageResource(poster!!)
        //rbDMovieRating.rating = movie.rating

        tvDMovieTitle.text = title
        tvPlot.text = plot
        ivDMoviePoster.setImageResource(poster!!)
        rbDMovieRating.rating = rating
        tvCast.text = cast
    }
}