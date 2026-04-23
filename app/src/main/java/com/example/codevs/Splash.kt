package com.example.codevs // Ensure this matches your actual project package

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.codevs.R

class Splash : AppCompatActivity() { // Changed class name to match your Manifest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val motionLayout = findViewById<MotionLayout>(R.id.main_layout)

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {}
            override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {}
            override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) {}

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                // This checks if the animation reached the 'end_state' defined in your XML
                if (currentId == R.id.end_state) {
                    // Navigate to SignIn instead of MainActivity
                    val intent = Intent(this@Splash, SignIn::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }
}