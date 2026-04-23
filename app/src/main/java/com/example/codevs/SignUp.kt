package com.example.codevs

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        setupSignInLink()
    }

    private fun setupSignInLink() {
        val tvSignInLink = findViewById<TextView>(R.id.tvSignInLink)
        val fullText = "Already have an account? Sign in"
        val clickablePart = "Sign in"

        val spannableString = SpannableString(fullText)

        val startIndex = fullText.indexOf(clickablePart)
        val endIndex = startIndex + clickablePart.length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // IMPORTANT: Ensure "Sign_in" matches your actual Login Activity class name
                val intent = Intent(this@SignUp, SignIn::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
                ds.isFakeBoldText = true
            }
        }

        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tvSignInLink.text = spannableString
        tvSignInLink.movementMethod = LinkMovementMethod.getInstance()
        tvSignInLink.highlightColor = Color.TRANSPARENT
    }
}