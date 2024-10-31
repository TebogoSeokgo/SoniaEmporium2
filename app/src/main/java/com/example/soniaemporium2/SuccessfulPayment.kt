package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SuccessfulPayment : AppCompatActivity() {

    private lateinit var backBtn: ImageButton
    private lateinit var totalPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_page)

        // Retrieve the total price from the intent
        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)

        // Initialize and display the total price in totalPriceTextView
        totalPriceTextView = findViewById(R.id.totalPrice2)
        totalPriceTextView.text = "Total Price: R$totalPrice"

        // Initialize the back button
        backBtn = findViewById(R.id.backbtn2)

        // Set click listener for back button to navigate back to MainActivity
        backBtn.setOnClickListener {
            Toast.makeText(this, "Returning to Homepage", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
