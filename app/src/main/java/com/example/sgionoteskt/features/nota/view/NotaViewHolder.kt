package com.example.sgionoteskt.features.nota.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sgionoteskt.R
import com.example.sgionoteskt.data.model.Nota
import com.example.sgionoteskt.data.model.NotaConEtiquetas
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class NotaViewHolder(
    itemView: View,
    private val onClick: (Nota) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val titulo = itemView.findViewById<TextView>(R.id.txtTitulo)
    private val contenido = itemView.findViewById<TextView>(R.id.txtContenido)
    private val chipGroup = itemView.findViewById<ChipGroup>(R.id.chipGroupEtiquetas)
    private val imgFavorito = itemView.findViewById<ImageView>(R.id.imgFavorito)
    private var notaActual: Nota? = null

    init {
        itemView.setOnClickListener {
            notaActual?.let { onClick(it) }
        }
    }

    fun bind(notaConEtiquetas: NotaConEtiquetas) {
        notaActual = notaConEtiquetas.nota
        titulo.text = notaConEtiquetas.nota.titulo
        contenido.text = notaConEtiquetas.nota.contenido

        imgFavorito.visibility = if (notaConEtiquetas.nota.esFavorito == 1) View.VISIBLE else View.GONE

        chipGroup.removeAllViews()

        val etiquetas = notaConEtiquetas.etiquetas
        val maxVisible = 3
        val visibleTags = etiquetas.take(maxVisible)
        val remaining = etiquetas.size - maxVisible
        val themeColor = ContextCompat.getColor(itemView.context, R.color.startGradient)

        visibleTags.forEach { etiqueta ->
            val chip = createChip(etiqueta.nombre, themeColor)
            chipGroup.addView(chip)
        }

        if (remaining > 0) {
            val moreChip = createChip("+$remaining", themeColor)
            chipGroup.addView(moreChip)
        }
    }

    private fun createChip(text: String, strokeColor: Int): Chip {
        return Chip(itemView.context).apply {
            this.text = text
            isClickable = false
            isCheckable = false
            setEnsureMinTouchTargetSize(false)
            chipMinHeight = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 28f, resources.displayMetrics
            )
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
            chipStartPadding = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics
            )
            chipEndPadding = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics
            )
            chipStrokeWidth = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics
            )
            chipStrokeColor = ColorStateList.valueOf(strokeColor)
            chipBackgroundColor = ColorStateList.valueOf(Color.WHITE)
            setTextColor(Color.DKGRAY)
        }
    }
}

