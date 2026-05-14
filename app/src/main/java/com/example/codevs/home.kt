package com.example.codevs

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // 1. Find the profile image view by its ID
        val profileImage = findViewById<ImageView>(R.id.profileImage)

        // 2. Set a click listener to trigger the navigation
// Inside home.kt
        profileImage.setOnClickListener {
            // Make sure it says Profile::class.java here!
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}