package com.example.codevs

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)

        // ── Search bar: pressing Enter/Search key navigates to Order ──
        val searchEdit = findViewById<android.widget.EditText>(R.id.searchEditText)
        searchEdit.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                goToOrder()
                true
            } else false
        }

        // ── Recent-search chips also navigate to Order page ──
        listOf(
            R.id.chipAircon,
            R.id.chipSmartTv,
            R.id.chipRef,
            R.id.chipRiceCooker
        ).forEach { id ->
            findViewById<TextView>(id).setOnClickListener { goToOrder() }
        }

        // ── Suggestion cards navigate to Order page ──
        listOf(
            R.id.cardPanasonic,
            R.id.cardHisense,
            R.id.cardTcl,
            R.id.cardCondura
        ).forEach { id ->
            // CardView extends ViewGroup — set click on the card itself
            findViewById<androidx.cardview.widget.CardView>(id).setOnClickListener { goToOrder() }
        }
    }

    private fun goToOrder() {
        val intent = Intent(this, Order::class.java)
        startActivity(intent)
    }
}