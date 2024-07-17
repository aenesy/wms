package com.lcwaikiki.wms.ui.tasima

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lcwaikiki.wms.R

class SupplyTable(
    private val items: List<SupplyItem>,
    private val onItemClick: (SupplyItem) -> Unit
) : RecyclerView.Adapter<SupplyTable.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textBarkod: TextView = view.findViewById(R.id.textBarkod)
        val textUniteBarkod: TextView = view.findViewById(R.id.textUniteBarkod)
        val textOkutulan: TextView = view.findViewById(R.id.textOkutulan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.supply_table_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textBarkod.text = item.barkod
        holder.textUniteBarkod.text = item.uniteBarkod

        // Okutulan değerini SupplyActivity'deki okutulansayaciMap'inden al
        val okutulansayaci = (holder.itemView.context as? SupplyActivity)?.okutulansayaciMap?.get("${item.barkod}:${item.uniteBarkod}") ?: 0
        holder.textOkutulan.text = okutulansayaci.toString()

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = items.size
}

data class SupplyItem(val barkod: String, val uniteBarkod: String)