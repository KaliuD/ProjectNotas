package br.com.alura.projectnotas

import android.app.Application
import androidx.room.Room
import br.com.alura.projectnotas.data.AppDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class MyApplication: Application() {
    companion object{
        var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            database = Room.databaseBuilder(
                this@MyApplication,
                AppDataBase::class.java,
                "notas-db"
            ).build()
        }
    }
}