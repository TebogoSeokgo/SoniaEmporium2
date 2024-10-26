package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SuccessfulPayment : AppCompatActivity() {

    private lateinit var backbtn: ImageButton
    private lateinit var totalPriceTextView: TextView // Define totalPriceTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_successful_page)

        // Retrieve the total price from the intent
        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)

        // Display the total price
        totalPriceTextView = findViewById(R.id.totalPrice2)
        totalPriceTextView.text = "Total Price: R$totalPrice" // Corrected reference to totalPriceTextView


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