package br.com.alura.projectnotas.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.projectnotas.MyApplication
import br.com.alura.projectnotas.adapter.NotaAdapter
import br.com.alura.projectnotas.databinding.ActivityMainBinding
import br.com.alura.projectnotas.databinding.CardNoteBinding
import br.com.alura.projectnotas.extensions.toModel
import br.com.alura.projectnotas.model.Nota

class MainActivity : ComponentActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val bindingCard by lazy {
        CardNoteBinding.inflate(layoutInflater)
    }
    private lateinit var notaAdapter: NotaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        notaAdapter = NotaAdapter(this, getNotas() ?: emptyList())
        notaAdapter.onItemClick = { nota ->
            val intent = Intent(this, FormularioNotaActivity::class.java)
            intent.putExtra("ID_NOTA", nota.id) // Passe o ID da nota para a atividade do formulário
            startActivity(intent)
        }
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = notaAdapter

        acionaBotaoFormulario()

        val n = MyApplication.database?.dao?.getNotaPorId(7)
        Log.d("minhaTag", "Nota recuperada: $n")
    }

    override fun onResume() {
        super.onResume()
        notaAdapter.atualizarNotas(getNotas() ?: emptyList())
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