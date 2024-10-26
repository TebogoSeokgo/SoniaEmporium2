package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Checkout : AppCompatActivity() {

    private lateinit var backbtn: ImageButton
    private lateinit var checkoutButton: Button
    private lateinit var totalPriceTextView: TextView // Define totalPriceTextView

    // Define EditText variables for the address fields
    private lateinit var streetAddress: EditText
    private lateinit var city: EditText
    private lateinit var suburb: EditText
    private lateinit var country: EditText
    private lateinit var postalCode: EditText
    private lateinit var bankAccountNumber: EditText
    private lateinit var cardHolderName: EditText
    private lateinit var bankName: EditText
    private lateinit var cvv: EditText
    private lateinit var expiryDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)

        // Retrieve the total price from the intent
        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)

        // Display the total price
        totalPriceTextView = findViewById(R.id.totalPrice)
        totalPriceTextView.text = "Total Price: R$totalPrice"

        // Initialize EditTexts for user input
        streetAddress = findViewById(R.id.streetAddress)
        city = findViewById(R.id.city)
        suburb = findViewById(R.id.suburb)
        country = findViewById(R.id.country)
        postalCode = findViewById(R.id.postalCode)
        bankAccountNumber = findViewById(R.id.bankAccountNumber)
        cardHolderName = findViewById(R.id.cardHolderName)
        bankName = findViewById(R.id.bankName)
        cvv = findViewById(R.id.cvv)
        expiryDate = findViewById(R.id.expiryDate)

        backbtn = findViewById(R.id.backbtn)

        // Set click listener for back button to navigate back to Cart
        backbtn.setOnClickListener {
            Toast.makeText(this, "Going back to Cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
            finish()
        }

        checkoutButton = findViewById(R.id.checkoutButton)

        // Set click listener for checkout button
        checkoutButton.setOnClickListener {
            // Validate input and proceed with checkout
            if (validateInput()) {
                Toast.makeText(this, "Checkout successful!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, SuccessfulPayment::class.java)
                intent.putExtra("TOTAL_PRICE", totalPrice)
                startActivity(intent)
            }
        }
    }

    private fun validateInput(): Boolean {
        // Basic validation for empty fields
        return when {
            streetAddress.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your street address", Toast.LENGTH_SHORT).show()
                false
            }
            city.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your city", Toast.LENGTH_SHORT).show()
                false
            }
            suburb.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your suburb", Toast.LENGTH_SHORT).show()
                false
            }
            country.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your country", Toast.LENGTH_SHORT).show()
                false
            }
            postalCode.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your postal code", Toast.LENGTH_SHORT).show()
                false
            }
            bankAccountNumber.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your bank account number", Toast.LENGTH_SHORT).show()
                false
            }
            cardHolderName.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your card holder name", Toast.LENGTH_SHORT).show()
                false
            }
            bankName.text.isEmpty() -> {
                Toast.makeText(this, "Please enter your bank name", Toast.LENGTH_SHORT).show()
                false
            }
            cvv.text.isEmpty() || cvv.text.length != 3 -> {
                Toast.makeText(this, "Please enter a valid CVV", Toast.LENGTH_SHORT).show()
                false
            }
            expiryDate.text.isEmpty() -> {
                Toast.makeText(this, "Please enter the expiry date", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}
