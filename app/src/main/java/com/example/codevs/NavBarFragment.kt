package com.example.codevs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class NavBarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nav_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnMenu = view.findViewById<ImageButton>(R.id.btnMenu)
        val btnHome = view.findViewById<ImageButton>(R.id.btnHome)
        val btnShop = view.findViewById<ImageButton>(R.id.btnShop)
        val btnEmail = view.findViewById<ImageButton>(R.id.btnEmail)

        btnMenu.setOnClickListener {
            // Navigate to Menu or Profile screen
            val intent = Intent(requireActivity(), Profile::class.java)
            requireActivity().startActivity(intent)
        }

        btnHome.setOnClickListener {
            // Navigate to Home screen
            val intent = Intent(requireActivity(), home::class.java)
            requireActivity().startActivity(intent)
        }

        btnShop.setOnClickListener {
            // Navigate to Shop screen (create this activity when needed)
            // val intent = Intent(requireActivity(), Shop::class.java)
            // requireActivity().startActivity(intent)
        }

        btnEmail.setOnClickListener {
            // Navigate to Email/Messages screen (create this activity when needed)
            // val intent = Intent(requireActivity(), Email::class.java)
            // requireActivity().startActivity(intent)
        }
    }
}
