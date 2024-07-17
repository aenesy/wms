package com.lcwaikiki.wms.ui.tasima

import android.os.Bundle
import android.util.Log
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
    internal val okutulansayaciMap = mutableMapOf<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_supply)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.supplyR)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            SupplyItem("R000056081", "34BH9599"),
            SupplyItem("R000056081", "34BH9599"),
            SupplyItem("R000056081", "34BH9599"),
            SupplyItem("R000056081", "34BH9599"),
            SupplyItem("R000056922", "06EZ6984"),
            SupplyItem("R000056892", "34AFA555"),
            SupplyItem("R000056605", "34BLE68"),
            SupplyItem("R000056060", "34EHP303")
        )

        adapter = SupplyTable(items) { clickedItem ->
            // Özdeş SupplyItem için okutulan sayacını artır
            val key = "${clickedItem.barkod}:${clickedItem.uniteBarkod}"
            val okutulansayaci = okutulansayaciMap.getOrDefault(key, 0) + 1
            okutulansayaciMap[key] = okutulansayaci

            // Logcat'e yazdır
            Log.d("SupplyActivity", "Tıklanan Öğe: Barkod=${clickedItem.barkod}, " +
                    "UniteBarkod=${clickedItem.uniteBarkod}, Okutulan=$okutulansayaci")

            // Adapter'ı güncelle
            adapter.notifyDataSetChanged()
        }

        recyclerView.adapter = adapter
    }
}
