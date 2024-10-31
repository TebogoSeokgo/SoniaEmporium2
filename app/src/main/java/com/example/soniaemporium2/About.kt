package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class About : AppCompatActivity() {

    // Declare variable for the back button
    private lateinit var backbtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        // Initialize the back button by finding it in the layout
        backbtn = findViewById(R.id.backbtn)

        // Set click listener for back button to navigate back to MainActivity
        backbtn.setOnClickListener {
            Toast.makeText(this, "Homepage", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}