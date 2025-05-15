package com.example.bmicalculator

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.bmicalculator.databinding.ActivityScaloraHomeScreenBinding

class ScaloraHomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityScaloraHomeScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScaloraHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup SharedPreferences
        sharedPreferences = getSharedPreferences("ScaloraPrefs", MODE_PRIVATE)

        // Setup Toolbar
        setSupportActionBar(binding.homeToolbar1)

        // Set default fragment
        loadFragment(ScaloraInformationFragment())

        // Handle bottom navigation item selection
        binding.homeBottomNavigation1.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navHome1 -> {
                    loadFragment(ScaloraInformationFragment())
                    true
                }

                R.id.navDetails1 -> {
                    loadFragment(ScaloraBMIFragment())
                    true
                }

                R.id.navLogout1 -> {
                    performLogout()
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.homeFragmentContainer1, fragment)
            .commit()
    }

    private fun performLogout() {
        // Only set isLoggedIn to false
        sharedPreferences.edit {
            putBoolean("isLoggedIn", false)
        }
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

        // Navigate to login screen
        val intent = Intent(this, ScaloraOnBoardScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
