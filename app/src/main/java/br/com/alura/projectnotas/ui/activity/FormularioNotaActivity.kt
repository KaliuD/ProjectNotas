package br.com.alura.projectnotas.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.projectnotas.MyApplication
import br.com.alura.projectnotas.databinding.ActivityFormularioNotaBinding
import br.com.alura.projectnotas.databinding.FormularioNotaDialogBinding
import br.com.alura.projectnotas.extensions.toEntity
import br.com.alura.projectnotas.model.Nota
import coil.load

class FormularioNotaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioNotaBinding.inflate(layoutInflater)
    }
    private val listaDeUrls = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bindingDialog = FormularioNotaDialogBinding.inflate(layoutInflater)

        binding.buttonAdicionarImagens.setOnClickListener{
            AlertDialog.Builder(this)
                .setView(bindingDialog.root)
                .setPositiveButton("Confirmar"){_,_ ->
                    if (bindingDialog.formularioImagemUrl.text.toString().isNotEmpty()){
                        listaDeUrls.add(bindingDialog.formularioImagemUrl.text.toString())
                        exibirTodasAsImagens()
                    }
                }
                .setNegativeButton("Cancelar"){_,_ ->

                }
                .show()
        }

        binding.buttonSalvar.setOnClickListener{
            if (binding.editTextConteudo.toString().isNotBlank()){
                val campoTitulo = binding.editTextTitulo.text.toString() ?: "Sem titulo"
                val campoImagens = listaDeUrls.joinToString(", ")
                val nota = Nota(
                    binding.editTextConteudo.text.toString(),
                    campoTitulo,
                    campoImagens,
                    System.currentTimeMillis())
                MyApplication.database?.dao?.inserir(nota.toEntity())
            }
            finish()
            Log.d("MinhaTag", "Tabela: ${MyApplication.database?.dao?.getNotas()}")
        }

    }

    private fun exibirTodasAsImagens() {
        binding.imageView.setImageDrawable(null)

        for(url in listaDeUrls){
            binding.imageView.load(url)
        }
    }
}