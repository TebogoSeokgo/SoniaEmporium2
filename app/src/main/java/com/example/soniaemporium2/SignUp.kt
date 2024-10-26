package com.example.soniaemporium2

import android.content.Intent
import android.os.Bundle
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
    private lateinit var confirmPasswordEditText: EditText
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
        confirmPasswordEditText = findViewById(R.id.editTextText5)
        registerButton = findViewById(R.id.regbtn)
        loginButton = findViewById(R.id.logbtn)
        backButton = findViewById(R.id.backbtn)

        // Back button functionality
        backButton.setOnClickListener {
            finish() // Closes current activity
        }

        // Register button click listener
        registerButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Validate input
            if (validateInput(firstName, lastName, email, password, confirmPassword)) {
                registerUser(email, password)
            }
        }

        // Login button click listener
        loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    // Validate input fields
    private fun validateInput(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return when {
            firstName.isEmpty() -> {
                firstNameEditText.error = "First name required"
                false
            }
            lastName.isEmpty() -> {
                lastNameEditText.error = "Last name required"
                false
            }
            email.isEmpty() -> {
                emailEditText.error = "Email required"
                false
            }
            password.isEmpty() -> {
                passwordEditText.error = "Password required"
                false
            }
            confirmPassword.isEmpty() -> {
                confirmPasswordEditText.error = "Confirm your password"
                false
            }
            password != confirmPassword -> {
                confirmPasswordEditText.error = "Passwords do not match"
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
                    // Redirect to Main Activity or another activity
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Registration failed
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
