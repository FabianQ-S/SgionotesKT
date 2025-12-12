package com.example.sgionoteskt.features.nota.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sgionoteskt.R
import com.example.sgionoteskt.data.model.Nota

class NotaViewHolder(
    itemView: View,
    private val onClick: (Nota) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val titulo = itemView.findViewById<TextView>(R.id.txtTitulo)
    private val contenido = itemView.findViewById<TextView>(R.id.txtContenido)
    private var notaActual: Nota? = null

    init {
        itemView.setOnClickListener {
            notaActual?.let { onClick(it) }
        }
    }

    fun bind(nota: Nota) {
        notaActual = nota
        titulo.text = nota.titulo
        contenido.text = nota.contenido
    }
}
