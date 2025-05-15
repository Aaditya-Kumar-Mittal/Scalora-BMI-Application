package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityScaloraOnBoardScreenBinding

class ScaloraOnBoardScreen : AppCompatActivity() {

    private lateinit var binding: ActivityScaloraOnBoardScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityScaloraOnBoardScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { root, windowInset ->
            val inset = windowInset.getInsets(WindowInsetsCompat.Type.systemBars())
            root.setPadding(inset.left, inset.top, inset.right, inset.bottom)
            windowInset
        }

        binding.onboardButton1.setOnClickListener {
            val intent = Intent(this, ScaloraSignUpScreen::class.java)
            startActivity(intent)
        }
    }
}