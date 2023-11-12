package br.com.alura.projectnotas.model

data class Nota(
    var conteudo: String,
    var titulo: String? = "Sem Titulo",
    var capa: String? = "Sem Capa",
    val id: Int = 0
)