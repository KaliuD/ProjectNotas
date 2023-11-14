package br.com.alura.projectnotas.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Notas::class],
    version = 3
)
abstract class AppDataBase: RoomDatabase() {
    abstract val dao: NotasDao
}