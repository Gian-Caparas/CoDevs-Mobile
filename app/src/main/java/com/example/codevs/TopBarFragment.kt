package com.example.codevs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment

class TopBarFragment : Fragment(R.layout.fragment_top_bar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileImage = view.findViewById<ImageView>(R.id.profileImage)
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)

        // Redirect to Profile Screen
        profileImage.setOnClickListener {
            val intent = Intent(requireContext(), Profile::class.java)
            startActivity(intent)
        }

        // Redirect to Search Screen
        searchEditText.setOnClickListener {
            val intent = Intent(requireContext(), Search::class.java)
            startActivity(intent)
        }
    }
}