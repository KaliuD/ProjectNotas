package br.com.alura.projectnotas.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotasDao {
    @Query("SELECT * FROM notas")
    fun getNotas(): List<Notas>

    @Insert
    fun inserir(nota: Notas)

    @Query("DELETE FROM notas WHERE id = :id")
    fun deletar(id: Int)

    @Query("Select * FROM notas WHERE id=:id")
    fun getNotaPorId(id: Int): Notas

    @Query("UPDATE notas SET " +
            "conteudo=:conteudo, " +
            "titulo=:titulo, " +
            "imagens=:imagens " +
            "WHERE id=:id")
    fun atualizarNota(
        id: Int,
        conteudo: String,
        titulo: String?,
        imagens: String?
    )

}