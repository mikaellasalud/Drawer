package com.globe.drawer

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import com.globe.forms.LoginActivity
import com.globe.movies.MainMovieActivity
import com.globe.tipcalculator.TipCalculatorActivity
import com.google.android.material.navigation.NavigationView

// Declare main_menu.xml inside res/menu
// Use the menu as value for NavigationView's app:menu
// Link DrawerLayout and ActionBarDrawerToggle

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)

        // Declare the class for handling drawer toggle
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        // Add ActionBarDrawerToggle as Listener
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        //Required by ActionBarDrawerToggle
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.navView)
        navigationView.setNavigationItemSelectedListener(this)

        tvEmail = navigationView.getHeaderView(0).findViewById(R.id.tvEmail)

        val email = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(Constants.EMAIL, null)

        tvEmail.text = email

        //getSharedPreferences.edit().remove().apply()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            Log.d("Main Activity", "$(item.title}")

            return true
        }

        when(item.itemId){
            R.id.action_calculator->{
                Log.d("Main Activity", "Calculator")
            }
            R.id.action_movies->{
                Log.d("Main Activity", "Movies")
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_movies->{
                val moviesIntent = Intent(this, MainMovieActivity::class.java)
                startActivity(moviesIntent)
            }
            R.id.action_calculator->{
                val calculatorIntent = Intent(this, TipCalculatorActivity::class.java)
                startActivity(calculatorIntent)
            }
            R.id.action_logout -> {

                AlertDialog.Builder(this)
                    .setTitle("Logout?")
                    .setPositiveButton("Cancel", object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int){

                        }
                    })
                    .setNegativeButton("OK", object: DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            Log.d("MainActivity", "Logout confirmed")

                            val prefs = getSharedPreferences(
                                Constants.PREFERENCE_NAME,
                                Context.MODE_PRIVATE
                            )

                            prefs.edit()
                                .remove(Constants.EMAIL)
                                .apply()
                            val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(loginIntent)
                            finish()

                        }

                    }).show()
                Log.d("Main Activity", "Logout")
            }
        }
        return false
    }
}