package br.com.alura.projectnotas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notas(
    val conteudo: String,
    var titulo: String?,
    var imagens: String? = "Sem Imagens",
    val dataDeCriacao: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
