package br.com.alura.projectnotas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import br.com.alura.projectnotas.data.AppDataBase
import br.com.alura.projectnotas.data.Notas
import br.com.alura.projectnotas.ui.theme.ProjectNotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val conteudo = "conteudo da nota"

        val nota = Notas(conteudo, "Segunda Nota")

        MyApplication.database?.dao?.inserir(nota)
        val notas = MyApplication.database?.dao?.getNotas()
        Log.d("Minha Tag", "Estas são as notas salvas: ${notas.toString()}")
    }
}