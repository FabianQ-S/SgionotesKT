package com.example.sgionoteskt.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EtiquetaConNotas(
    @Embedded val etiqueta: Etiqueta,
    @Relation(
        parentColumn = "id_etiqueta",
        entityColumn = "id_nota",
        associateBy = Junction(
            value = EtiquetaNotaCrossRef::class,
            parentColumn = "fk_id_etiqueta",
            entityColumn = "fk_id_nota"
        )
    )
    val notas: List<Nota>
)

