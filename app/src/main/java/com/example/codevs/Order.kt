package com.example.codevs

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Order : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        // ── Product data ──
        data class Product(
            val name: String,
            val unitPrice: Int
        )

        val products = mapOf(
            R.id.btnOrderProduct1 to Product("Aircon (Panasonic CW-U921JH)", 29500),
            R.id.btnOrderProduct2 to Product("Aircon (Carrier FP-53CKV030308)", 39500),
            R.id.btnOrderProduct3 to Product("Aircon (Hitachi RAS-08HT)", 40500)
        )

        // ── Wire each Order button ──
        products.forEach { (btnId, product) ->
            findViewById<Button>(btnId).setOnClickListener {
                showOrderModal(product.name, product.unitPrice)
            }
        }

        // ── Contact Us button ──
        findViewById<Button>(R.id.btnContactUs).setOnClickListener {
            val intent = Intent(this, Contact::class.java)
            startActivity(intent)
        }
    }

    private fun showOrderModal(productName: String, unitPrice: Int) {
        // CHANGED: Instantiated a standard dialog instance instead of a BottomSheet
        val dialog = Dialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_order_modal, null)
        dialog.setContentView(view)

        // References
        val tvName      = view.findViewById<TextView>(R.id.modalProductName)
        val tvPrice     = view.findViewById<TextView>(R.id.modalProductPrice)
        val tvQty       = view.findViewById<TextView>(R.id.tvQuantity)
        val tvTotal     = view.findViewById<TextView>(R.id.modalTotalCost)
        val btnIncrease = view.findViewById<Button>(R.id.btnIncreaseQty)
        val btnDecrease = view.findViewById<Button>(R.id.btnDecreaseQty)
        val btnConfirm  = view.findViewById<Button>(R.id.btnConfirmOrder)

        var quantity = 1

        fun formatPrice(amount: Int) = "₱ %,d".format(amount)

        // Populate
        tvName.text  = "Product: $productName"
        tvPrice.text = "Price: ${formatPrice(unitPrice)}"
        tvQty.text   = "1"
        tvTotal.text = "Total cost: ${formatPrice(unitPrice)}"

        // Increase
        btnIncrease.setOnClickListener {
            quantity++
            tvQty.text   = quantity.toString()
            tvTotal.text = "Total cost: ${formatPrice(unitPrice * quantity)}"
        }

        // Decrease (min = 1)
        btnDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tvQty.text   = quantity.toString()
                tvTotal.text = "Total cost: ${formatPrice(unitPrice * quantity)}"
            }
        }

        // Confirm
        btnConfirm.setOnClickListener {
            Toast.makeText(
                this,
                "Order confirmed! Qty: $quantity × ${formatPrice(unitPrice)}",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }

        // ── DIALOG WINDOW CUSTOM OVERLAYS FIX ──
        dialog.window?.apply {
            // 1. Clears out the default white box background so xml corners render rounded
            setBackgroundDrawableResource(android.R.color.transparent)

            // 2. Adjusts modal viewport dimensions so it handles all device sizes beautifully
            val width = (resources.displayMetrics.widthPixels * 0.85).toInt() // Takes up exactly 85% width
            setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)

            // 3. Optional visual fidelity - guarantees background dimming effect stays active
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0.5f) // Adjust overlay darkness percent here (0.0f to 1.0f)
        }

        dialog.show()
    }
}