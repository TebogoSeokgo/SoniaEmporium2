package com.example.soniaemporium2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Cart : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView
    private lateinit var backbtn: ImageButton
    private lateinit var checkbtn: Button
    private lateinit var database: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().getReference("checkouts")

        // Calculate the total price
        val totalPrice = Shop.cartItems.sumOf { it.price }

        // Display total price
        totalPriceTextView = findViewById(R.id.totalPriceTextView)
        totalPriceTextView.text = "Total Price: R$totalPrice"

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cartAdapter = CartAdapter(Shop.cartItems) { item ->
            Shop.cartItems.remove(item)
            cartAdapter.notifyDataSetChanged()
            Toast.makeText(this, "${item.name} removed from cart", Toast.LENGTH_SHORT).show()

            // Update total price after item removal
            val newTotalPrice = Shop.cartItems.sumOf { it.price }
            totalPriceTextView.text = "Total Price: R$newTotalPrice"
        }

        recyclerView.adapter = cartAdapter

        // Handle Checkout button click
        checkbtn = findViewById(R.id.checkbtn)
        checkbtn.setOnClickListener {
            saveCheckoutDataToFirebase(totalPrice)

            // Start Checkout activity
            val intent = Intent(this, Checkout::class.java)
            intent.putExtra("TOTAL_PRICE", totalPrice)
            startActivity(intent)
            finish()
        }

        // Handle Back button click
        backbtn = findViewById(R.id.backbtn)
        backbtn.setOnClickListener {
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Function to save checkout data to Firebase
    private fun saveCheckoutDataToFirebase(totalPrice: Double) {
        val checkoutId = database.push().key  // Generate unique ID for each checkout
        val checkoutData = HashMap<String, Any>()

        // Add each item with quantity and price
        checkoutData["items"] = Shop.cartItems.map {
            mapOf("name" to it.name, "price" to it.price)
        }
        checkoutData["totalPrice"] = totalPrice

        checkoutId?.let {
            database.child(it).setValue(checkoutData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Checkout saved successfully!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save checkout data.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
