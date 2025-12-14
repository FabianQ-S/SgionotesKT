package com.example.sgionoteskt.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.sgionoteskt.data.model.Etiqueta
import com.example.sgionoteskt.data.model.EtiquetaConNotas
import kotlinx.coroutines.flow.Flow

@Dao
interface EtiquetaDao {

    @Insert
    suspend fun insertar(etiqueta: Etiqueta): Long

    @Update
    suspend fun actualizar(etiqueta: Etiqueta)

    @Delete
    suspend fun eliminar(etiqueta: Etiqueta)

    @Query("SELECT * FROM etiquetas ORDER BY es_favorito DESC, fecha_favorito DESC, nombre ASC")
    fun obtenerEtiquetasFlow(): Flow<List<Etiqueta>>

    @Query("SELECT * FROM etiquetas ORDER BY es_favorito DESC, fecha_favorito DESC, nombre ASC")
    suspend fun obtenerEtiquetas(): List<Etiqueta>

    @Transaction
    @Query("SELECT * FROM etiquetas WHERE id_etiqueta = :id")
    suspend fun obtenerEtiquetaConNotas(id: Int): EtiquetaConNotas
}

