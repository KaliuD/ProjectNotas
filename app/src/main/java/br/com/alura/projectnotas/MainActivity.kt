package br.com.alura.projectnotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        recyclerView.adapter = getNotas()?.let { NotaAdapter(this, it) }
    }

    private fun getNotas(): List<Nota>? {
        return MyApplication.database?.dao?.getNotas()?.map { e -> e.toModel() }
    }


}