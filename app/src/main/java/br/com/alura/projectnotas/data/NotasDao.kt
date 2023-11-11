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

    @Delete
    fun deletar(nota: Notas)

}