package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Shop: AppCompatActivity() {

    companion object {
        val cartItems: MutableList<CartItem> = mutableListOf() // List to hold items added to the cart
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        // Initializing Views
        val backButton: ImageButton = findViewById(R.id.backbtn)
        val cartButton: ImageButton = findViewById(R.id.Cartbtn)
        val addButton1: Button = findViewById(R.id.addbtn)
        val addButton2: Button = findViewById(R.id.addbtn2)
        val addButton3: Button = findViewById(R.id.addbtn3)
        val addButton4: Button = findViewById(R.id.addbtn4)

        // Example for Spinner (Size selection for Yellow Pair)
        val sizeSpinner: Spinner = findViewById(R.id.sizeSpinner)
        val sizeOptions = arrayOf("XS", "S", "M", "L", "XL")
        val sizeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sizeOptions)
        sizeSpinner.adapter = sizeAdapter

        // Setting up other Spinners for different items
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = sizeAdapter

        val spinner2: Spinner = findViewById(R.id.spinner2)
        spinner2.adapter = sizeAdapter

        val spinner3: Spinner = findViewById(R.id.spinner3)
        spinner3.adapter = sizeAdapter

        // Setting Click Listeners for Add/Remove Buttons
        addButton1.setOnClickListener {
            val selectedSize = sizeSpinner.selectedItem.toString()
            cartItems.add(CartItem("Yellow Pair(R899.99)- Size: $selectedSize", R.drawable.yellowpair, 899.99))
            Toast.makeText(this, "Added Yellow Pair to Cart", Toast.LENGTH_SHORT).show()
        }


        addButton2.setOnClickListener {
            val selectedSize = spinner.selectedItem.toString()
            cartItems.add(CartItem("Floral Pair (R999.99)- Size: $selectedSize", R.drawable.floralpair, 999.99))
            Toast.makeText(this, "Added Floral Pair to Cart", Toast.LENGTH_SHORT).show()
        }


        addButton3.setOnClickListener {
            val selectedSize = spinner2.selectedItem.toString()
            cartItems.add(CartItem("Cream Dress (R799.99)- Size: $selectedSize", R.drawable.creamdress, 799.99))
            Toast.makeText(this, "Added Cream Dress to Cart", Toast.LENGTH_SHORT).show()
        }


        addButton4.setOnClickListener {
            val selectedSize = spinner3.selectedItem.toString()
            cartItems.add(CartItem("Pink Top (R599.99)- Size: $selectedSize", R.drawable.pinktop, 599.99))
            Toast.makeText(this, "Added Cream Skirt to Cart", Toast.LENGTH_SHORT).show()
        }


        // Back Button Action
        backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }

        cartButton.setOnClickListener {
            // Calculate total price
            val totalPrice = cartItems.sumOf { it.price }

            // Navigate to Cart activity with the total price
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

            Toast.makeText(this, "Total price: R$totalPrice", Toast.LENGTH_SHORT).show()
        }

    }
}
