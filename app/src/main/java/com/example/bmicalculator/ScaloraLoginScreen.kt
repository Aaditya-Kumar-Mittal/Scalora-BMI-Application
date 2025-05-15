package com.example.bmicalculator

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.bmicalculator.databinding.ActivityScaloraLoginScreenBinding

class ScaloraLoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityScaloraLoginScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScaloraLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("ScaloraPrefs", MODE_PRIVATE)

        binding.loginSignup1.setOnClickListener {
            startActivity(Intent(this, ScaloraSignUpScreen::class.java))
        }

        binding.loginButton1.setOnClickListener {
            val inputEmail = binding.loginEmailInput1.text.toString()
            val inputPassword = binding.loginPasswordInput1.text.toString()
            val storedEmail = sharedPreferences.getString("email", null)
            val storedPassword = sharedPreferences.getString("password", null)

            if (inputEmail == storedEmail && inputPassword == storedPassword) {
                sharedPreferences.edit() {
                    putBoolean("isLoggedIn", true)
                }
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ScaloraHomeScreen::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
