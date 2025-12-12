package com.example.sgionoteskt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "etiquetas_has_notas",
    primaryKeys = ["fk_id_etiqueta", "fk_id_nota"],
    foreignKeys = [
        ForeignKey(
            entity = Etiqueta::class,
            parentColumns = ["id_etiqueta"],
            childColumns = ["fk_id_etiqueta"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Nota::class,
            parentColumns = ["id_nota"],
            childColumns = ["fk_id_nota"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EtiquetaNotaCrossRef(
    @ColumnInfo(name = "fk_id_etiqueta") val idEtiqueta: Int,
    @ColumnInfo(name = "fk_id_nota") val idNota: Int
)
