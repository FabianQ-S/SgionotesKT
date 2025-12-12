package com.example.sgionoteskt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.sgionoteskt.data.model.Etiqueta
import com.example.sgionoteskt.data.model.EtiquetaConNotas

@Dao
interface EtiquetaDao {

    @Insert
    suspend fun insertar(etiqueta: Etiqueta): Long

    @Query("SELECT * FROM etiquetas ORDER BY nombre ASC")
    suspend fun obtenerEtiquetas(): List<Etiqueta>

    @Transaction
    @Query("SELECT * FROM etiquetas WHERE id_etiqueta = :id")
    suspend fun obtenerEtiquetaConNotas(id: Int): EtiquetaConNotas
}
