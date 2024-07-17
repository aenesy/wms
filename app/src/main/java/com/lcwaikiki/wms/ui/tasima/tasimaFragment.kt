package com.lcwaikiki.wms.ui.tasima

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcwaikiki.wms.R
import com.lcwaikiki.wms.databinding.FragmentGalleryBinding

class tasimaFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TableAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tasimaViewModel =
            ViewModelProvider(this).get(tasimaViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = root.findViewById(R.id.tableRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val items = listOf(
            TableItem("R000056081", "34BH9599", "KAPI002"),
            TableItem("R000056922", "06EZ6984", "KAPI012"),
            TableItem("R000056892", "34AFA555", "KAPI012"),
            TableItem("R000056605", "34BLE68", "KAPI002"),
            TableItem("R000056060", "34EHP303", "KAPI004")
        )

        adapter = TableAdapter(items) { clickedItem ->
            // Yeni Activity'yi başlat
            val intent = Intent(activity, SupplyActivity::class.java).apply {
                putExtra("RANDEVU_KODU", clickedItem.randevuKodu)
                putExtra("ARAC_PLAKA", clickedItem.aracPlaka)
                putExtra("KAPI", clickedItem.kapi)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}