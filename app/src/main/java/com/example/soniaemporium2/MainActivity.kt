package com.example.soniaemporium2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var homebtn: ImageButton
    private lateinit var shopbtn: ImageButton
    private lateinit var blogbtn: ImageButton
    private lateinit var aboutbtn: ImageButton
    private lateinit var contactbtn: ImageButton
    private lateinit var cartbtn: ImageButton
    private lateinit var cart2btn: ImageButton
    private lateinit var cart3btn: ImageButton
    private lateinit var cart6btn: ImageButton
    private lateinit var cart5btn: ImageButton
    private lateinit var adminbtn: ImageButton
    private lateinit var signupbtn: Button
    private lateinit var loginbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons
        homebtn = findViewById(R.id.homebtn)
        shopbtn = findViewById(R.id.shopbtn)
        blogbtn = findViewById(R.id.blogbtn)
        aboutbtn = findViewById(R.id.aboutbtn)
        contactbtn = findViewById(R.id.contactbtn)
        cartbtn = findViewById(R.id.cartbtn)
        cart2btn = findViewById(R.id.cart2btn)
        cart3btn = findViewById(R.id.cart3btn)
        cart6btn = findViewById(R.id.cart6btn)
        cart5btn = findViewById(R.id.cart5btn)
        signupbtn = findViewById(R.id.signupbtn)
        loginbtn = findViewById(R.id.loginbtn)

        // Set up click listeners for the buttons

        signupbtn.setOnClickListener {
            showToast("Sign Up")
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener {
            showToast("Login")
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        homebtn.setOnClickListener {
            showToast("Home")
            // Add any actions for Home button
        }

        shopbtn.setOnClickListener {
            showToast("Shop")
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
        }

        blogbtn.setOnClickListener {
            showToast("Blog")
            val intent = Intent(this, Blog::class.java)
            startActivity(intent)
        }

        aboutbtn.setOnClickListener {
            showToast("About")
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }

        contactbtn.setOnClickListener {
            showToast("Contact")
            val intent = Intent(this, Contact::class.java)
            startActivity(intent)
        }

        cartbtn.setOnClickListener {
            showToast("Cart")
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        cart2btn.setOnClickListener {
            showToast("Shop")
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
        }

        cart3btn.setOnClickListener {
            showToast("Shop")
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
        }

        cart6btn.setOnClickListener {
            showToast("Shop")
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
        }

        cart5btn.setOnClickListener {
            showToast("Shop")
            val intent = Intent(this, Shop::class.java)
            startActivity(intent)
        }
    }

    // Helper function to show toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
