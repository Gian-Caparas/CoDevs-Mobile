package com.example.codevs

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

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
            val intent = android.content.Intent(this, Contact::class.java)
            startActivity(intent)
        }
    }

    /**
     * Shows a BottomSheetDialog that matches the "Modal - Order" mockup.
     * Quantity can be incremented / decremented and total updates live.
     */
    private fun showOrderModal(productName: String, unitPrice: Int) {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
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
            // TODO: send order to backend / cart
            android.widget.Toast.makeText(
                this,
                "Order confirmed! Qty: $quantity × ${formatPrice(unitPrice)}",
                android.widget.Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }

        dialog.show()
    }
}