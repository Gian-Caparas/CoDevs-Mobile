package com.example.codevs

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Contact : AppCompatActivity() {

    private lateinit var btnConfirm: Button
    private lateinit var etFirstName: TextInputEditText
    private lateinit var etLastName: TextInputEditText
    private lateinit var etContact: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etDescription: TextInputEditText
    private lateinit var etOtherService: TextInputEditText
    private lateinit var serviceDropdown: AutoCompleteTextView
    private lateinit var otherServiceLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact)

        // 1. Initialize all views
        btnConfirm = findViewById(R.id.btnConfirm)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etContact = findViewById(R.id.etContact)
        etEmail = findViewById(R.id.etEmail)
        etDescription = findViewById(R.id.etDescription)
        etOtherService = findViewById(R.id.etOtherService)
        serviceDropdown = findViewById(R.id.serviceDropdown)
        otherServiceLayout = findViewById(R.id.otherServiceLayout)

        // 2. Navigation: The Back Button
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // This safely closes the activity and returns to the previous screen
        }

        // 3. Setup the Dropdown Options
        val services = arrayOf("Software Development", "Hardware repairs", "UI/UX", "Report an Issue", "OTHER")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, services)
        serviceDropdown.setAdapter(adapter)

        // Listen for "OTHER" selection
        serviceDropdown.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = adapter.getItem(position)
            if (selectedItem == "OTHER") {
                otherServiceLayout.visibility = View.VISIBLE
            } else {
                otherServiceLayout.visibility = View.GONE
                etOtherService.text?.clear() // Clear it if they change their mind
            }
            checkFields() // Re-validate fields when dropdown changes
        }

        // Catch data from the Profile Screen
        val prefillData = intent.getStringExtra("PREFILL_SERVICE")
        if (prefillData != null) {
            serviceDropdown.setText(prefillData, false)
            checkFields() // Validate immediately after pre-filling
        }

        // 4. Form Validation Setup
        setupTextWatchers()

        // 5. The Confirm Alert Dialog
        btnConfirm.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Your message has been sent successfully. We will get back to you soon!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    finish() // Returns user to previous screen after success
                }
                .show()
        }

        val profileImage = findViewById<ImageView>(R.id.profileImage)

        profileImage.setOnClickListener {
            // Make sure it says Profile::class.java here!
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }

    // This attaches a listener to every input field so it checks validation every time a letter is typed
    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { checkFields() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        etFirstName.addTextChangedListener(textWatcher)
        etLastName.addTextChangedListener(textWatcher)
        etContact.addTextChangedListener(textWatcher)
        etEmail.addTextChangedListener(textWatcher)
        etDescription.addTextChangedListener(textWatcher)
        etOtherService.addTextChangedListener(textWatcher)
        serviceDropdown.addTextChangedListener(textWatcher)
    }

    // The logic that actually decides if the button should be enabled
    private fun checkFields() {
        val isOtherSelected = serviceDropdown.text.toString() == "OTHER"
        val isOtherFilled = if (isOtherSelected) etOtherService.text.toString().isNotBlank() else true

        val allFilled = etFirstName.text.toString().isNotBlank() &&
                etLastName.text.toString().isNotBlank() &&
                etContact.text.toString().isNotBlank() &&
                etEmail.text.toString().isNotBlank() &&
                etDescription.text.toString().isNotBlank() &&
                serviceDropdown.text.toString().isNotBlank() &&
                isOtherFilled

        btnConfirm.isEnabled = allFilled
    }
}