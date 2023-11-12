package br.com.alura.projectnotas.extensions

import br.com.alura.projectnotas.data.Notas
import br.com.alura.projectnotas.model.Nota

fun Notas.toModel(): Nota{
    return Nota(this.conteudo,this.titulo,this.capa,this.id)
}
fun Notas.toEntity(): Notas{
    return Notas(this.conteudo,this.titulo,this.capa,this.id)
}
