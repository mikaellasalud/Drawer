package com.globe.forms

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.globe.drawer.Constants
import com.globe.drawer.MainActivity
import com.globe.todo.R

class LoginActivity : AppCompatActivity() {

    private lateinit var ivAppLogo:ImageView
    private lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(isLoggedIn()){
            toMainActivity()
            finish()
            return
        }
        supportActionBar?.title = "Login"

        ivAppLogo = findViewById(R.id.ivAppLogo)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("LoginActivity","Login button clicked")

                login()
            }
        })

    }

    private fun login() {

        val isValidPassword = isCredentialsValid()
        if(!isValidPassword){
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        Log.d("LoginActivity","email: $email password:$password")

        saveCredentials()
        toMainActivity()
        finish()
    }

    private fun toMainActivity() {

        val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)

        startActivity(mainIntent)
    }

    private fun isCredentialsValid() : Boolean {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        return email == "mikaella.salud@globe.com.ph" && password == "password123"
    }

    private fun getMySharedPreference() : SharedPreferences {
        return getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun isLoggedIn() : Boolean {
        val sharedPreferences = getMySharedPreference()

        val email = sharedPreferences.getString(Constants.EMAIL, null)

        return email != null
    }

    private fun saveCredentials() {
        val sharedPreferences = getMySharedPreference()

        val email = etEmail.text.toString().trim()

        sharedPreferences
            .edit()
            .putString(Constants.EMAIL, email)
            .apply()
    }

}
