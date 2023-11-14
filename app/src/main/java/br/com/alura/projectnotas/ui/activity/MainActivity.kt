package br.com.alura.projectnotas.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.projectnotas.MyApplication
import br.com.alura.projectnotas.adapter.NotaAdapter
import br.com.alura.projectnotas.databinding.ActivityMainBinding
import br.com.alura.projectnotas.extensions.toModel
import br.com.alura.projectnotas.model.Nota

class MainActivity : ComponentActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NotaAdapter(this, getNotas() ?: throw NullPointerException("Notas Vazias"))

        acionaBotaoFormulario()
//        recyclerView.adapter = getNotas()?.let { NotaAdapter(this, it) } //backup caso de errado
    }

    private fun getNotas(): List<Nota>? {
        return MyApplication.database?.dao?.getNotas()?.map { e -> e.toModel() }
    }

    private fun acionaBotaoFormulario(){
        binding.criarNota.setOnClickListener{
            val intent = Intent(this, FormularioNotaActivity::class.java)
            startActivity(intent)
        }
    }


}