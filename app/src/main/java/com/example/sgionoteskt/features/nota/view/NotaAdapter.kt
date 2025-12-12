package com.example.sgionoteskt.features.nota.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.sgionoteskt.R
import com.example.sgionoteskt.data.model.Nota

class NotaAdapter(
    private val onClick: (Nota) -> Unit
) : ListAdapter<Nota, NotaViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Nota>() {
        override fun areItemsTheSame(oldItem: Nota, newItem: Nota) =
            oldItem.idNota == newItem.idNota

        override fun areContentsTheSame(oldItem: Nota, newItem: Nota) =
            oldItem == newItem
    }
}


