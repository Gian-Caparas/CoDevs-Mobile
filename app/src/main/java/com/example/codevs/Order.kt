package com.example.codevs

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

class Order : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        val contactBtn = findViewById<Button>(R.id.btnContactUs)
        contactBtn.setOnClickListener {
            startActivity(Intent(this, Contact::class.java))
        }

        // 1. Locate the horizontal row layouts from layout container view matching IDs
        val containerAircons = findViewById<LinearLayout>(R.id.containerAircons)
        val containerTVs = findViewById<LinearLayout>(R.id.containerTVs)
        val containerFridges = findViewById<LinearLayout>(R.id.containerFridges)
        val containerRiceCookers = findViewById<LinearLayout>(R.id.containerRiceCookers)


        // 2. Row: Air Conditioners (Existing catalog data check)
        addProductCard(containerAircons, "Panasonic", "CW-U921JH", 29500, "★★★★★", R.drawable.panasonic)
        addProductCard(containerAircons, "Carrier", "FP-53CKV030", 39500, "★★★★★", R.drawable.carrier)
        addProductCard(containerAircons, "Hitachi", "RAS-08HT", 40500, "★★★★☆", R.drawable.hitachi)
        addProductCard(containerAircons, "Daikin", "DK-INVERT9", 34200, "★★★★★", R.drawable.daikin)

        // 3. Row: Smart TVs (Added 5 Appliances)
        addProductCard(containerTVs, "Samsung", "Neo QLED 55\"", 28000, "★★★★★", R.drawable.samsung)
        addProductCard(containerTVs, "LG", "OLED C3 48\"", 65000, "★★★★★", R.drawable.lg)
        addProductCard(containerTVs, "Sony", "Bravia X80L 50\"", 36500, "★★★★☆", R.drawable.sony)
        addProductCard(containerTVs, "TCL", "QLED C645 55\"", 22900, "★★★★☆", R.drawable.tcl)
        addProductCard(containerTVs, "Xiaomi", "Google TV A2 43\"", 14500, "★★★★★", R.drawable.xiaomi)

        // 4. Row: Refrigerators (Added 5 Appliances)
        addProductCard(containerFridges, "Panasonic", "Inverter 2-Door", 24500, "★★★★★", R.drawable.panasonic2)
        addProductCard(containerFridges, "Samsung", "No Frost Double", 32000, "★★★★★", R.drawable.samsung2)
        addProductCard(containerFridges, "LG", "Smart Top-Mount", 27800, "★★★★☆", R.drawable.lg2)
        addProductCard(containerFridges, "Condura", "Home Ultima 1D", 11200, "★★★★☆", R.drawable.condura)
        addProductCard(containerFridges, "Sharp", "Inverter Side-by-Side", 48900, "★★★★★", R.drawable.sharp)

        // 5. Row: Rice Cookers (Added 5 Appliances)
        addProductCard(containerRiceCookers, "Tiger", "Conventional 1.0L", 3800, "★★★★★", R.drawable.tiger)
        addProductCard(containerRiceCookers, "Imarflex", "IRJ1500A 1.8L", 12500, "★★★★★", R.drawable.imarflex)
        addProductCard(containerRiceCookers, "Though Mama", "NTMRC18WM 1.8L", 4200, "★★★★☆", R.drawable.toughmama)
        addProductCard(containerRiceCookers, "Panasonic", "Non-Stick 1.5L", 2400, "★★★★☆", R.drawable.panasonic3)
        addProductCard(containerRiceCookers, "Hanabishi", "Jar Type 1.8L", 1500, "★★★★★", R.drawable.hanabishi)
    }

    // Helper logic to dynamically build unique view anchors for fragments side-by-side
    private fun addProductCard(targetRow: LinearLayout, brand: String, model: String, price: Int, rating: String, imageResId: Int) {
        val frameAnchor = FragmentContainerView(this).apply {
            id = android.view.View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        targetRow.addView(frameAnchor)

        // Formats integer currency to string placeholder display for layout text views
        val formattedPriceString = "₱ %,d".format(price)

        // Change this block inside your addProductCard function:
        val productFragment = ProductCardFragment.newInstance(brand, model, formattedPriceString, rating, imageResId)
        productFragment.setOnOrderClickListener {
            showOrderModal(model, price, imageResId) // FIXED: Added imageResId here
        }

        supportFragmentManager.beginTransaction()
            .replace(frameAnchor.id, productFragment)
            .commit()
    }

    // Replace your existing showOrderModal function with this updated version:
    private fun showOrderModal(productName: String, unitPrice: Int, imageResId: Int) { // FIXED: Added imageResId parameter
        val dialog = Dialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_order_modal, null)
        dialog.setContentView(view)

        // References
        val imgProduct  = view.findViewById<android.widget.ImageView>(R.id.modalProductImage) // FIXED: Grabbed the ImageView layout handle
        val tvName      = view.findViewById<TextView>(R.id.modalProductName)
        val tvPrice     = view.findViewById<TextView>(R.id.modalProductPrice)
        val tvQty       = view.findViewById<TextView>(R.id.tvQuantity)
        val tvTotal     = view.findViewById<TextView>(R.id.modalTotalCost)
        val btnIncrease = view.findViewById<Button>(R.id.btnIncreaseQty)
        val btnDecrease = view.findViewById<Button>(R.id.btnDecreaseQty)
        val btnConfirm  = view.findViewById<Button>(R.id.btnConfirmOrder)

        var quantity = 1
        fun formatPrice(amount: Int) = "₱ %,d".format(amount)

        // Populate Layout elements values
        tvName.text  = "Product: $productName"
        tvPrice.text = "Price: ${formatPrice(unitPrice)}"
        imgProduct.setImageResource(imageResId) // FIXED: Updates the modal preview icon to match the chosen item
        tvQty.text   = "1"
        tvTotal.text = "Total cost: ${formatPrice(unitPrice)}"

        // Increase listener action handle
        btnIncrease.setOnClickListener {
            quantity++
            tvQty.text   = quantity.toString()
            tvTotal.text = "Total cost: ${formatPrice(unitPrice * quantity)}"
        }

        // Decrease listener action handle (minimum volume bounds set at index = 1)
        btnDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tvQty.text   = quantity.toString()
                tvTotal.text = "Total cost: ${formatPrice(unitPrice * quantity)}"
            }
        }

        // Confirm button completion event process logic tracking
        btnConfirm.setOnClickListener {
            Toast.makeText(
                this,
                "Order confirmed! Qty: $quantity × ${formatPrice(unitPrice)}",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }

        dialog.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            val width = (resources.displayMetrics.widthPixels * 0.85).toInt() // Takes up exactly 85% width
            setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0.5f)
        }

        dialog.show()
    }
}