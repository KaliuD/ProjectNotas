package br.com.alura.projectnotas

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.alura.projectnotas.data.AppDataBase

open class MyApplication: Application() {
    companion object{
        var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        //TO-DO: Mudar "allowMainThreadQueries"
        database = Room
            .databaseBuilder(this, AppDataBase::class.java, "notas-db")
            .allowMainThreadQueries()
            .build()
    }
}