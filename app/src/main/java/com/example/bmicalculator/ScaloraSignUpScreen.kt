package com.example.bmicalculator

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.bmicalculator.databinding.ActivityScaloraSignUpScreenBinding

class ScaloraSignUpScreen : AppCompatActivity() {
    private lateinit var binding: ActivityScaloraSignUpScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScaloraSignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("ScaloraPrefs", MODE_PRIVATE)

        binding.signupLogin1.setOnClickListener {
            startActivity(Intent(this, ScaloraLoginScreen::class.java))
        }

        binding.signupButton1.setOnClickListener {
            val name = binding.signupNameInput1.text.toString()
            val email = binding.signupEmailInput1.text.toString()
            val password = binding.signupPasswordInput1.text.toString()
            val confirmPassword = binding.signupConfirmPasswordInput1.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                sharedPreferences.edit() {
                    putString("name", name)
                    putString("email", email)
                    putString("password", password)
                    putBoolean("isLoggedIn", true)
                }
                Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ScaloraHomeScreen::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields correctly.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
