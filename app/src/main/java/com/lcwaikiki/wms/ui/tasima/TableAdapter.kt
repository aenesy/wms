package com.lcwaikiki.wms.ui.tasima

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lcwaikiki.wms.R

class TableAdapter(private val items: List<TableItem>,private val onItemClick: (TableItem) -> Unit) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textRandevuKodu: TextView = view.findViewById(R.id.textRandevuKodu)
        val textAracPlaka: TextView = view.findViewById(R.id.textAracPlaka)
        val textKapi: TextView = view.findViewById(R.id.textKapi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Burada öğe layoutunu çağırıyoruz

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textRandevuKodu.text = item.randevuKodu
        holder.textAracPlaka.text = item.aracPlaka
        holder.textKapi.text = item.kapi

        // Tıklama işlemini burada ekleyebilirsiniz
        holder.itemView.setOnClickListener {
                onItemClick(item)
        }
    }

    override fun getItemCount() = items.size
}

// Veri sınıfı
data class TableItem(val randevuKodu: String, val aracPlaka: String, val kapi: String)