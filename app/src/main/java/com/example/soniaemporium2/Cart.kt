package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class Cart : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView
    private lateinit var backbtn: ImageButton
    private lateinit var checkbtn: Button
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        firestore = FirebaseFirestore.getInstance()
        totalPriceTextView = findViewById(R.id.totalPriceTextView)

        updateTotalPriceDisplay()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cartAdapter = CartAdapter(Shop.cartItems, { item ->
            Shop.cartItems.remove(item)
            cartAdapter.notifyDataSetChanged()
            Toast.makeText(this, "${item.name} removed from cart", Toast.LENGTH_SHORT).show()
            updateTotalPriceDisplay()
        }, {
            updateTotalPriceDisplay() // update total when quantity changes
        })

        recyclerView.adapter = cartAdapter

        checkbtn = findViewById(R.id.checkbtn)
        checkbtn.setOnClickListener {
            val currentTotalPrice = Shop.cartItems.sumOf { it.price * it.quantity }
            saveCheckoutDataToFirestore(currentTotalPrice)

            val intent = Intent(this, Checkout::class.java)
            intent.putExtra("TOTAL_PRICE", currentTotalPrice)
            startActivity(intent)
            finish()
        }

        backbtn = findViewById(R.id.backbtn)
        backbtn.setOnClickListener {
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun saveCheckoutDataToFirestore(totalPrice: Double) {
        val checkoutData = hashMapOf(
            "items" to Shop.cartItems.map { item ->
                mapOf("name" to item.name, "price" to item.price, "quantity" to item.quantity)
            },
            "totalPrice" to totalPrice
        )

        firestore.collection("checkouts")
            .add(checkoutData)
            .addOnSuccessListener {
                Toast.makeText(this, "Checkout saved successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save checkout data.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateTotalPriceDisplay() {
        val newTotalPrice = Shop.cartItems.sumOf { it.price * it.quantity }
        totalPriceTextView.text = "Total Price: R$newTotalPrice"
    }
}
