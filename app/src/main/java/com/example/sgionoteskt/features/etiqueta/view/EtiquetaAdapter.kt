package com.example.sgionoteskt.features.etiqueta.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sgionoteskt.R
import com.example.sgionoteskt.data.model.Etiqueta

class EtiquetaAdapter(
    private val onFavoriteClick: (Etiqueta) -> Unit,
    private val onEditComplete: (Etiqueta, String) -> Unit,
    private val onDeleteClick: (Etiqueta) -> Unit
) : ListAdapter<Etiqueta, EtiquetaAdapter.EtiquetaViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtiquetaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tag_editable, parent, false)
        return EtiquetaViewHolder(view, onFavoriteClick, onEditComplete, onDeleteClick)
    }

    override fun onBindViewHolder(holder: EtiquetaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EtiquetaViewHolder(
        itemView: View,
        private val onFavoriteClick: (Etiqueta) -> Unit,
        private val onEditComplete: (Etiqueta, String) -> Unit,
        private val onDeleteClick: (Etiqueta) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val etTagText = itemView.findViewById<EditText>(R.id.etTagText)
        private val btnMore = itemView.findViewById<ImageButton>(R.id.btnMore)
        private val ivTagIcon = itemView.findViewById<ImageView>(R.id.ivTagIcon)
        private val ivFavoriteIcon = itemView.findViewById<ImageView>(R.id.ivFavoriteIcon)
        private var etiquetaActual: Etiqueta? = null

        init {
            etTagText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val nuevoNombre = etTagText.text.toString().trim()
                    etiquetaActual?.let {
                        if (nuevoNombre.isNotEmpty()) {
                            onEditComplete(it, nuevoNombre)
                        }
                    }
                    etTagText.isFocusable = false
                    etTagText.isFocusableInTouchMode = false
                    val imm = itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(etTagText.windowToken, 0)
                    true
                } else {
                    false
                }
            }
        }

        fun bind(etiqueta: Etiqueta) {
            etiquetaActual = etiqueta
            etTagText.setText(etiqueta.nombre)

            if (etiqueta.esFavorito == 1) {
                ivTagIcon.visibility = View.GONE
                ivFavoriteIcon.visibility = View.VISIBLE
            } else {
                ivTagIcon.visibility = View.VISIBLE
                ivFavoriteIcon.visibility = View.GONE
            }

            etTagText.isFocusable = false
            etTagText.isFocusableInTouchMode = false

            btnMore.setOnClickListener {
                showPopupMenu(it, etiqueta)
            }
        }

        private fun showPopupMenu(anchor: View, etiqueta: Etiqueta) {
            val popup = PopupMenu(anchor.context, anchor)
            popup.menuInflater.inflate(R.menu.menu_tag_options, popup.menu)

            val favItem = popup.menu.findItem(R.id.menu_add_favorite)
            if (etiqueta.esFavorito == 1) {
                favItem.title = "Quitar de favoritos"
            } else {
                favItem.title = "Añadir a favoritos"
            }

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_add_favorite -> {
                        onFavoriteClick(etiqueta)
                        true
                    }
                    R.id.menu_edit -> {
                        etTagText.isFocusable = true
                        etTagText.isFocusableInTouchMode = true
                        etTagText.requestFocus()
                        etTagText.setSelection(etTagText.text.length)
                        val imm = anchor.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(etTagText, InputMethodManager.SHOW_IMPLICIT)
                        true
                    }
                    R.id.menu_delete -> {
                        onDeleteClick(etiqueta)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Etiqueta>() {
        override fun areItemsTheSame(oldItem: Etiqueta, newItem: Etiqueta) =
            oldItem.idEtiqueta == newItem.idEtiqueta

        override fun areContentsTheSame(oldItem: Etiqueta, newItem: Etiqueta) =
            oldItem == newItem
    }
}
