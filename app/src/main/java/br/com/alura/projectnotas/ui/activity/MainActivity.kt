package br.com.alura.projectnotas.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.projectnotas.MyApplication
import br.com.alura.projectnotas.adapter.NotaAdapter
import br.com.alura.projectnotas.databinding.ActivityMainBinding
import br.com.alura.projectnotas.databinding.CardNoteBinding
import br.com.alura.projectnotas.extensions.toEntity
import br.com.alura.projectnotas.extensions.toModel
import br.com.alura.projectnotas.model.Nota
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        notaAdapter.onExcluirClick = { nota ->
            CoroutineScope(Dispatchers.IO).launch{
                MyApplication.database?.dao?.deletar(nota.id)
                val notas = MyApplication.database?.dao?.getNotas()?.map { e -> e.toModel() }

                withContext(Dispatchers.Main) {
                    notaAdapter.atualizarNotas(notas ?: emptyList())
                }
            }
        }
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = notaAdapter

        acionaBotaoFormulario()

    }

    override fun onResume() {
        super.onResume()
        var notas:List<Nota>? = null
        CoroutineScope(Dispatchers.IO).launch{
            notas = MyApplication.database?.dao?.getNotas()?.map { e -> e.toModel() }

            withContext(Dispatchers.Main) {
                notaAdapter.atualizarNotas(notas ?: emptyList())
            }
        }
    }

    private fun getNotas(): List<Nota>? {
        var notas:List<Nota>? = null
        CoroutineScope(Dispatchers.IO).launch{
            notas = MyApplication.database?.dao?.getNotas()?.map { e -> e.toModel() }

            withContext(Dispatchers.Main) {
                notaAdapter.atualizarNotas(notas ?: emptyList())
            }
        }
        return notas
    }

    private fun acionaBotaoFormulario(){
        binding.criarNota.setOnClickListener{
            val intent = Intent(this, FormularioNotaActivity::class.java)
            startActivity(intent)
        }
    }


}