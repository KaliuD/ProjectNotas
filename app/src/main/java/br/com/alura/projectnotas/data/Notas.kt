package br.com.alura.projectnotas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notas(
    val conteudo: String,
    var titulo: String ,
    var capa: String? = "Sem Capa",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
