package com.lcwaikiki.wms.ui.tasima

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcwaikiki.wms.R

class SupplyActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SupplyTable
    private lateinit var editTextUnite: EditText
    private lateinit var editTextBarkod: EditText
    private val itemMap = mutableMapOf<Pair<String, String>, SupplyItem>()
    private var totalBarkod = 0
    private var totalUnite = 0
    private val uniqueUnites = mutableSetOf<String>()
    private lateinit var textViewTotalBarkod: TextView
    private lateinit var textViewTotalUnite: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_supply)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textViewTotalBarkod = findViewById(R.id.textViewTotalBarkod)
        textViewTotalUnite = findViewById(R.id.textViewTotalUnite)
        recyclerView = findViewById(R.id.supplyR)
        recyclerView.layoutManager = LinearLayoutManager(this)

        editTextUnite = findViewById(R.id.editTextUnite)
        editTextBarkod = findViewById(R.id.editTextBarkod)

        adapter = SupplyTable(itemMap.values.toList())
        recyclerView.adapter = adapter

        editTextUnite.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE) {
                editTextBarkod.requestFocus()
                true
            } else {
                false
            }
        }

        editTextBarkod.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_NULL) {
                handleBarcodeInput()
                true
            } else {
                false
            }
        }

    }

    private fun handleBarcodeInput() {
        val barkod = editTextBarkod.text.toString()
        val unite = editTextUnite.text.toString()

        if (barkod.isNotEmpty() && unite.isNotEmpty()) {
            val key = Pair(barkod, unite)
            if (itemMap.containsKey(key)) {
                itemMap[key]?.okutulan = itemMap[key]?.okutulan?.plus(1) ?: 1
            } else {
                itemMap[key] = SupplyItem(barkod, unite, 1)
                if (uniqueUnites.add(unite)) {
                    totalUnite++
                    textViewTotalUnite.text = "Toplam Ünite: $totalUnite"
                }
            }
            totalBarkod++
            textViewTotalBarkod.text = "Toplam Barkod: $totalBarkod"

            adapter.updateItems(itemMap.values.toList())
            editTextBarkod.text.clear()
        }
        val uniteKeyboardIcon = findViewById<ImageView>(R.id.uniteKeyboardIcon)
        uniteKeyboardIcon.setOnClickListener {
            editTextUnite.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editTextUnite, InputMethodManager.SHOW_IMPLICIT)
        }

    }

}


