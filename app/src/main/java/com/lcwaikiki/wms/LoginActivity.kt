package com.lcwaikiki.wms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.lcwaikiki.wms.databinding.ActivityLoginBinding
import java.util.*
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedLanguage = getSavedLanguage()
        setLocale(savedLanguage)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.passwordInput.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                login_button_clk()
                true
            } else {
                false
            }
        }
            // Apply saved language before setting content view


        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinner = findViewById(R.id.language_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.languages,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Set the spinner selection based on the saved language
        spinner.setSelection(if (savedLanguage == "en") 1 else 0)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val languageCode = when (position) {
                    0 -> "tr" // Türkçe
                    1 -> "en" // İngilizce
                    else -> "tr" // Default to Turkish
                }
                if (languageCode != getSavedLanguage()) {
                    setLocale(languageCode)
                    saveLanguage(languageCode)
                    recreate()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun saveLanguage(languageCode: String) {
        val sharedPref = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("SelectedLanguage", languageCode)
            apply()
        }
    }

    private fun getSavedLanguage(): String {
        val sharedPref = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        return sharedPref.getString("SelectedLanguage", "tr") ?: "tr"
    }

    fun login_button_clk() {
        val usernameInput= binding.usernameInput
        val passwordInput= binding.passwordInput
        val username = usernameInput.text?.toString() ?: ""
        val password = passwordInput.text?.toString() ?: ""

        when {
            username.isEmpty() -> {
                showToast(getString(R.string.username_empty))
                usernameInput.requestFocus()
            }
            password.isEmpty() -> {
                showToast(getString(R.string.password_empty))
                passwordInput.requestFocus()
            }
            else -> {
                // Here you would typically validate the credentials against a database or API
                // For this example, we'll just proceed to the MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Optional: close LoginActivity so user can't go back to it
            }
        }


    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}