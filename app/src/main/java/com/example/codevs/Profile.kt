package com.example.codevs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.codevs.R.*

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val reportIssueBtn = findViewById<Button>(id.btnReportIssue) // Use your actual button ID
        reportIssueBtn.setOnClickListener {
            val intent = Intent(this, Contact::class.java)

            // Attach the sticky note! This matches the exact string our Contact screen is listening for.
            intent.putExtra("PREFILL_SERVICE", "Report an Issue")

            startActivity(intent)
        }


    }
}