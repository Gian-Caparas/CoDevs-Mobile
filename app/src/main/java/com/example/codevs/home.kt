package com.example.codevs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // Profile image → Profile screen
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        profileImage.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }

        // Search bar click → Search screen
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        searchEditText.setOnClickListener {
            startActivity(Intent(this, Search::class.java))
        }
        // Also trigger on focus (user taps into the field)
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                startActivity(Intent(this, Search::class.java))
            }
        }

        // Contact button → Contact screen
        val contactBtn = findViewById<Button>(R.id.contactButton)
        contactBtn.setOnClickListener {
            startActivity(Intent(this, Contact::class.java))
        }
    }
}