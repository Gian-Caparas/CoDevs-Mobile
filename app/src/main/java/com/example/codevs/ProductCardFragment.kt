package com.example.codevs

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProductCardFragment : Fragment(R.layout.fragment_product_card) {

    // FIXED: Added functional tracking callback property variable
    private var onOrderClickListener: (() -> Unit)? = null

    fun setOnOrderClickListener(listener: () -> Unit) {
        this.onOrderClickListener = listener
    }

    companion object {
        private const val ARG_BRAND = "brand"
        private const val ARG_MODEL = "model"
        private const val ARG_PRICE = "price"
        private const val ARG_RATING = "rating"
        private const val ARG_IMAGE = "image"

        fun newInstance(brand: String, model: String, price: String, rating: String, imageResId: Int): ProductCardFragment {
            val fragment = ProductCardFragment()
            val args = Bundle().apply {
                putString(ARG_BRAND, brand)
                putString(ARG_MODEL, model)
                putString(ARG_PRICE, price)
                putString(ARG_RATING, rating)
                putInt(ARG_IMAGE, imageResId)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgProduct = view.findViewById<ImageView>(R.id.imgProduct)
        val tvBrand = view.findViewById<TextView>(R.id.tvBrand)
        val tvModel = view.findViewById<TextView>(R.id.tvModel)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvRating = view.findViewById<TextView>(R.id.tvRating)
        val btnOrder = view.findViewById<Button>(R.id.btnOrderProduct)

        arguments?.let {
            tvBrand.text = "Brand: ${it.getString(ARG_BRAND)}"
            tvModel.text = "Model: ${it.getString(ARG_MODEL)}"
            tvPrice.text = it.getString(ARG_PRICE)
            tvRating.text = it.getString(ARG_RATING)
            imgProduct.setImageResource(it.getInt(ARG_IMAGE))
        }

        // FIXED: Swapped out placeholder toast message to trigger active listener handler
        btnOrder.setOnClickListener {
            onOrderClickListener?.invoke()
        }
    }
}