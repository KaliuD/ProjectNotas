package br.com.alura.projectnotas.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Notas::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract val dao: NotasDao
}