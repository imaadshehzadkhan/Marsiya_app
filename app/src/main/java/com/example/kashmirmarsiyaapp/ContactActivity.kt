package com.example.kashmirmarsiyaapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        // Find views by ID
        val firstNameEditText: EditText = findViewById(R.id.name)
        val lastNameEditText: EditText = findViewById(R.id.lastname)
        val emailEditText: EditText = findViewById(R.id.Email)
        val messageEditText: EditText = findViewById(R.id.etMessage)
        val privacyPolicyCheckBox: CheckBox = findViewById(R.id.privacyPolicy)
        val submitButton: Button = findViewById(R.id.button2)


        submitButton.setOnClickListener {

            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val message = messageEditText.text.toString().trim()
            val isPrivacyPolicyChecked = privacyPolicyCheckBox.isChecked
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && message.isNotEmpty() && isPrivacyPolicyChecked) {
                Toast.makeText(this, "Form Submitted", Toast.LENGTH_SHORT).show()

                println("First Name: $firstName")
                println("Last Name: $lastName")
                println("Email: $email")
                println("Message: $message")
                println("Privacy Policy Accepted: $isPrivacyPolicyChecked")
            } else {
                Toast.makeText(this, "Please fill all fields and accept the privacy policy", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
