package com.globe.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globe.drawer.R

class MainMovieActivity : AppCompatActivity() {

    private lateinit var rvMovies: RecyclerView
    private val movieAdapter = MovieAdapter()

    private val movies: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviemain)

        rvMovies = findViewById(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        movieAdapter.onItemClickListener = object:OnItemClickListener{

            override fun onItemClicked(position: Int) {
                Log.d("MainActivity", "$position")
                val selectedMovie = movieAdapter.movies.get(position)
                Log.d("MainActivity", "Selected Movie: $selectedMovie")
                showMovieDetails(position)
            }
        }

        initializeMovies()


    }

    private fun showMovieDetails(moviePosition: Int) {
        val selectedMovie = movieAdapter.movies.get(moviePosition)

        val movieDetailIntent = Intent(this, MovieDetailActivity::class.java)
        //movieDetailIntent.putExtra(Constants.MOVIE, selectedMovie)
        movieDetailIntent.putExtra(Constants.TITLE, selectedMovie.title)
        movieDetailIntent.putExtra(Constants.POSTER, selectedMovie.poster)
        movieDetailIntent.putExtra(Constants.RATING, selectedMovie.rating)
        movieDetailIntent.putExtra(Constants.PLOT, selectedMovie.plot)
        movieDetailIntent.putExtra(Constants.CAST, selectedMovie.cast)

        startActivity(movieDetailIntent)


        Log.d("MainActivity", "Selected Movie:$selectedMovie")
    }

    private fun initializeMovies(){
        movies.add(Movie("Thor", 2022, R.drawable.thor,3.0F, "Thor embarks on a journey unlike anything he's ever faced -- a quest for inner peace. However, his retirement gets interrupted by Gorr the God Butcher, a galactic killer who seeks the extinction of the gods. To combat the threat, Thor enlists the help of King Valkyrie, Korg and ex-girlfriend Jane Foster, who -- to his surprise -- inexplicably wields his magical hammer. Together, they set out on a harrowing cosmic adventure to uncover the mystery of the God Butcher's vengeance.\n" ,"Cast: Chris Hemsworth, Christian Bale, Natalie Portman, \n Taika Waititi, Russell Crowe, Tessa Thompson, \n Brett Goldstein"))
        movies.add(Movie("Captain America", 2011, R.drawable.captainamerica,4.5F, "It is 1941 and the world is in the throes of war. Steve Rogers (Chris Evans) wants to do his part and join America's armed forces, but the military rejects him because of his small stature. Finally, Steve gets his chance when he is accepted into an experimental program that turns him into a supersoldier called Captain America. Joining forces with Bucky Barnes (Sebastian Stan) and Peggy Carter (Hayley Atwell), Captain America leads the fight against the Nazi-backed HYDRA organization.","Cast: Chris Evans, Anthony Mackie, Hayley Atwell,\n Sebastian Stan, Scarlett Johansson, Emily VanCamp, \n Frank Grillo, Stan Lee"))
        movies.add(Movie("Captain Marvel", 2019, R.drawable.captainmarvel,4.0F, "Captain Marvel is an extraterrestrial Kree warrior who finds herself caught in the middle of an intergalactic battle between her people and the Skrulls. Living on Earth in 1995, she keeps having recurring memories of another life as U.S. Air Force pilot Carol Danvers. With help from Nick Fury, Captain Marvel tries to uncover the secrets of her past while harnessing her special superpowers to end the war with the evil Skrulls.\n","Cast: Brie Larson, Samuel L. Jackson, Gemma Chan, \n Lashana Lynch, Jude Law, Mckenna Grace, \n Ben Medelsohn, Jude Law, Dijmon Hounsou"))
        movies.add(Movie("SpiderMan", 2021, R.drawable.spiderman,5.0F, "With Spider-Man's identity now revealed, our friendly neighborhood web-slinger is unmasked and no longer able to separate his normal life as Peter Parker from the high stakes of being a superhero. When Peter asks for help from Doctor Strange, the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.\n","Cast: Tom Holland, Zendaya, Benedict Cumberbatch, \n Jacob Batalon, Jon Favreau, Jamie Foxx, Willem Dafoe,\n Alfred Molina, Benedict Wong, Tony Revolori, \n Marisa Tomei, Andrew Garfield, Tobey Maguire"))
        movies.add(Movie("Iron Man", 2010, R.drawable.ironman,5.0F, "With the world now aware that he is Iron Man, billionaire inventor Tony Stark (Robert Downey Jr.) faces pressure from all sides to share his technology with the military. He is reluctant to divulge the secrets of his armored suit, fearing the information will fall into the wrong hands. With Pepper Potts (Gwyneth Paltrow) and \"Rhodey\" Rhodes (Don Cheadle) by his side, Tony must forge new alliances and confront a powerful new enemy.\n","Cast: Robert Downey Jr., Jon Favreau, Mickey Rourke,\n Don Cheadle, Scarlet Johansson, Gwyneth Paltrow, \n Sam Rockwell, Stan Lee"))
        movies.add(Movie("Ant Man", 2015, R.drawable.antman,4.0F, "Forced out of his own company by former protégé Darren Cross, Dr. Hank Pym (Michael Douglas) recruits the talents of Scott Lang (Paul Rudd), a master thief just released from prison. Lang becomes Ant-Man, trained by Pym and armed with a suit that allows him to shrink in size, possess superhuman strength and control an army of ants. The miniature hero must use his new skills to prevent Cross, also known as Yellowjacket, from perfecting the same technology and using it as a weapon for evil.\n","Cast: Paul Rudd, Evangeline Lilly, Michael Douglas, \n Michael Pena, David Dastmalchian, Kevin Feige, \n Judy Greer, Bobby Cannavale"))
        movies.add(Movie("Avengers",2018, R.drawable.avengers,5.0F, "Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy yet -- the evil Thanos. On a mission to collect all six Infinity Stones, Thanos plans to use the artifacts to inflict his twisted will on reality. The fate of the planet and existence itself has never been more uncertain as everything the Avengers have fought for has led up to this moment.\n","Cast: Robert Downey Jr., Chris Hemsworth, Josh Brolin, \n Chris Evans, Scarlett Johansson, Tom Holland,\n Mark Ruffalo, Elizabeth Olsen"))
        movies.add(Movie("ShangChi",2021,R.drawable.shangchi,4.0F, "Martial-arts master Shang-Chi confronts the past he thought he left behind when he's drawn into the web of the mysterious Ten Rings organization.\n","Cast: Simu Liu, Awkwafina, Stephanie Hsu, \n Tony Leung Chiu-wai, Michelle Yeoh, Meng'er Zhang,\n Benedict Wong, Ronny Chieng"))
        movies.add(Movie("Doctor Strange",2022, R.drawable.doctorstrange,3.0F, "Doctor Strange teams up with a mysterious teenage girl from his dreams who can travel across multiverses, to battle multiple threats, including other-universe versions of himself, which threaten to wipe out millions across the multiverse.\n", "Cast: Benedict Cumberbatch, Elizabeth Olsen, Xochitl Gomez, \n Rachel McAdams, John Krasinski, Hayley Atwell, \n LAshana Lynch, Bruce Campbell"))
        movies.add(Movie("Black Panther",2018, R.drawable.blackpanther,4.0F, "After the death of his father, T'Challa returns home to the African nation of Wakanda to take his rightful place as king. When a powerful enemy suddenly reappears, T'Challa's mettle as king -- and as Black Panther -- gets tested when he's drawn into a conflict that puts the fate of Wakanda and the entire world at risk. Faced with treachery and danger, the young king must rally his allies and release the full power of Black Panther to defeat his foes and secure the safety of his people.\n","Cast: Chadwick Boseman, Michael B. Jordan, Lupita Nyong'o, \n Letitia Wright, Danai Gurira, Winston Duke, \n Daniel Kaluuya, Angela Bassett"))

        movieAdapter.movies = movies

        rvMovies.adapter = movieAdapter
    }
}