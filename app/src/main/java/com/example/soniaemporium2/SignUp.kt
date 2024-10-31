package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize UI components
        firstNameEditText = findViewById(R.id.editTextText2)
        lastNameEditText = findViewById(R.id.editTextText3)
        emailEditText = findViewById(R.id.editTextText)
        passwordEditText = findViewById(R.id.editTextNumberPassword)
        registerButton = findViewById(R.id.regbtn)
        loginButton = findViewById(R.id.logbtn)
        backButton = findViewById(R.id.backbtn)

        // Back button functionality
        backButton.setOnClickListener {
            finish() // Closes current activity
        }

        // Register button click listener
        registerButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validate input
            if (validateInput(firstName, lastName, email, password)) {
                registerUser(email, password)
            }
        }

        // Login button click listener
        loginButton.setOnClickListener {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    // Validate input fields
    private fun validateInput(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$".toRegex() // Requires at least one lowercase, uppercase, digit, special character, and minimum 8 characters

        return when {
            firstName.isBlank() -> {
                firstNameEditText.error = "First name required"
                false
            }
            lastName.isBlank() -> {
                lastNameEditText.error = "Last name required"
                false
            }
            email.isBlank() -> {
                emailEditText.error = "Email required"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailEditText.error = "Enter a valid email"
                false
            }
            password.isBlank() -> {
                passwordEditText.error = "Password required"
                false
            }
            !password.matches(passwordPattern) -> {
                passwordEditText.error = "Password must contain at least 8 characters, including upper and lower case letters, a number, and a special character"
                false
            }
            else -> true
        }
    }

    // Register user with Firebase
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    // Redirect to Login activity
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Registration failed, provide feedback
                    Toast.makeText(this, "Registration failed: ${task.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
