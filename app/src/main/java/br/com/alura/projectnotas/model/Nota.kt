package br.com.alura.projectnotas.model

data class Nota(
    var conteudo: String,
    var titulo: String? = "Sem Titulo",
    var imagens: String? = "Sem Imagens",
    val dataDeCriacao: Long = System.currentTimeMillis(),
    val id: Int = 0
)