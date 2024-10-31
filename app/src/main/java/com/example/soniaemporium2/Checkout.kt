package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class Checkout : AppCompatActivity() {

    private lateinit var backBtn: ImageButton
    private lateinit var checkoutButton: Button
    private lateinit var totalPriceTextView: TextView

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

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        firestore = FirebaseFirestore.getInstance()

        // Fetch and display total price
        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)
        totalPriceTextView = findViewById(R.id.totalPrice)
        totalPriceTextView.text = "Total Price: R$totalPrice"

        // Initialize UI components
        initializeUI()

        // Set listener for back button
        backBtn.setOnClickListener {
            Toast.makeText(this, "Going back to Cart", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Cart::class.java))
            finish()
        }

        // Set listener for checkout button
        checkoutButton.setOnClickListener {
            if (validateInput()) {
                saveCheckoutDataToFirestore(totalPrice)
                // Navigate to SuccessfulPayment activity
                startActivity(Intent(this, SuccessfulPayment::class.java).apply {
                    putExtra("TOTAL_PRICE", totalPrice)
                })
                finish()
            }
        }
    }

    private fun initializeUI() {
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
        checkoutButton = findViewById(R.id.checkoutButton)
        backBtn = findViewById(R.id.backbtn)
    }

    private fun validateInput(): Boolean {
        return when {
            streetAddress.text.isEmpty() -> {
                streetAddress.error = "Please enter your street address"
                false
            }
            city.text.isEmpty() -> {
                city.error = "Please enter your city"
                false
            }
            suburb.text.isEmpty() -> {
                suburb.error = "Please enter your suburb"
                false
            }
            country.text.isEmpty() -> {
                country.error = "Please enter your country"
                false
            }
            postalCode.text.isEmpty() -> {
                postalCode.error = "Please enter your postal code"
                false
            }
            bankAccountNumber.text.isEmpty() -> {
                bankAccountNumber.error = "Please enter your bank account number"
                false
            }
            cardHolderName.text.isEmpty() -> {
                cardHolderName.error = "Please enter your card holder name"
                false
            }
            bankName.text.isEmpty() -> {
                bankName.error = "Please enter your bank name"
                false
            }
            cvv.text.isEmpty() || cvv.text.length != 3 -> {
                cvv.error = "Please enter a valid CVV"
                false
            }
            expiryDate.text.isEmpty() -> {
                expiryDate.error = "Please enter the expiry date"
                false
            }
            else -> true
        }
    }

    private fun saveCheckoutDataToFirestore(totalPrice: Double) {
        val uniqueId = UUID.randomUUID().toString() // Generate a unique ID for the document

        val paymentAndDelivery = hashMapOf(
            "streetAddress" to streetAddress.text.toString(),
            "city" to city.text.toString(),
            "suburb" to suburb.text.toString(),
            "country" to country.text.toString(),
            "postalCode" to postalCode.text.toString(),
            "bankAccountNumber" to bankAccountNumber.text.toString(),
            "cardHolderName" to cardHolderName.text.toString(),
            "bankName" to bankName.text.toString(),
            "cvv" to cvv.text.toString(),
            "expiryDate" to expiryDate.text.toString(),
            "totalPrice" to totalPrice
        )

        firestore.collection("checkouts")
            .document(uniqueId) // Save each checkout under a unique ID
            .set(paymentAndDelivery)
            .addOnSuccessListener {
                Toast.makeText(this, "Checkout saved successfully!", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save checkout data.", Toast.LENGTH_SHORT).show()
            }
    }
}
