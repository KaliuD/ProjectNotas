package br.com.alura.projectnotas.extensions

import android.widget.TextView
import br.com.alura.projectnotas.data.Notas
import br.com.alura.projectnotas.model.Nota

fun Notas.toModel(): Nota{
    return Nota(this.conteudo,this.titulo,this.imagens, this.dataDeCriacao,this.id)
}
fun Nota.toEntity(): Notas{
    return Notas(this.conteudo,this.titulo,this.imagens, this.dataDeCriacao)
}

