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

        // Contact button → Contact screen
        val contactBtn = findViewById<Button>(R.id.contactButton)
        contactBtn.setOnClickListener {
            startActivity(Intent(this, Contact::class.java))
        }
    }
}