package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var Loginbtn: Button
    private lateinit var Regbtn: Button
    private lateinit var backButton: ImageButton

    // Firebase Auth instance
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize UI elements
        emailInput = findViewById(R.id.editTextText)
        passwordInput = findViewById(R.id.editTextNumberPassword)
        Loginbtn = findViewById(R.id.Loginbtn)
        Regbtn = findViewById(R.id.Regbtn)
        backButton = findViewById(R.id.backbtn)

        // Set listener for login button
        Loginbtn.setOnClickListener {
            handleLogin()
        }

        // Set listener for register button
        Regbtn.setOnClickListener {
            Toast.makeText(this, "SignUp", Toast.LENGTH_SHORT).show()
            // Navigate to the sign-up activity
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // Set listener for back button to navigate to MainActivity
        backButton.setOnClickListener {
            Toast.makeText(this, "Homepage", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleLogin() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Authenticate with Firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Successful login, navigate to Cart activity
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Cart::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
