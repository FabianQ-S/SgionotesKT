package com.example.sgionoteskt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "etiquetas")
data class Etiqueta(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_etiqueta")
    val idEtiqueta: Int = 0,

    val nombre: String,

    @ColumnInfo(name = "es_favorito")
    val esFavorito: Int = 0,

    @ColumnInfo(name = "fecha_favorito")
    val fechaFavorito: Long? = null
)
