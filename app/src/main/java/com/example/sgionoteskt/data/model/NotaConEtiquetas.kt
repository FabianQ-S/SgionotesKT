package com.example.sgionoteskt.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class NotaConEtiquetas(
    @Embedded val nota: Nota,
    @Relation(
        parentColumn = "id_nota",
        entityColumn = "id_etiqueta",
        associateBy = Junction(
            value = EtiquetaNotaCrossRef::class,
            parentColumn = "fk_id_nota",
            entityColumn = "fk_id_etiqueta"
        )
    )
    val etiquetas: List<Etiqueta>
)

