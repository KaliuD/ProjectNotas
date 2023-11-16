package br.com.alura.projectnotas.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.projectnotas.MyApplication
import br.com.alura.projectnotas.R
import br.com.alura.projectnotas.data.Notas
import br.com.alura.projectnotas.databinding.ActivityFormularioNotaBinding
import br.com.alura.projectnotas.databinding.FormularioNotaDialogBinding
import br.com.alura.projectnotas.extensions.toEntity
import br.com.alura.projectnotas.model.Nota
import coil.load

class FormularioNotaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioNotaBinding.inflate(layoutInflater)
    }
    private var listaDeUrls = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bindingDialog = FormularioNotaDialogBinding.inflate(layoutInflater)

        adicionarImagens(bindingDialog)

        val notaId = intent.getIntExtra("ID_NOTA", -1)
        Log.d("MinhaTag", "AQUI!!! "+notaId.toString())
        if (notaId != -1) {
            val nota = MyApplication.database?.dao?.getNotaPorId(notaId)
            // Agora você tem a nota, exiba as informações na tela do formulário
            exibirInformacoesNota(nota)

            alterar(notaId)
        }else salvar()
    }

    private fun adicionarImagens(bindingDialog: FormularioNotaDialogBinding) {
        binding.buttonAdicionarImagens.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(bindingDialog.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    if (bindingDialog.formularioImagemUrl.text.toString().isNotEmpty()) {
                        listaDeUrls.add(bindingDialog.formularioImagemUrl.text.toString())
                        exibirTodasAsImagens()
                    }
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }

    private fun salvar() {
        binding.buttonSalvar.setOnClickListener {
            if (binding.editTextConteudo.toString().isNotBlank()) {
                val campoTitulo = binding.editTextTitulo.text.toString() ?: "Sem titulo"
                val campoImagens = listaDeUrls.joinToString(", ")
                val nota = Nota(
                    binding.editTextConteudo.text.toString(),
                    campoTitulo,
                    campoImagens,
                    System.currentTimeMillis()
                )
                MyApplication.database?.dao?.inserir(nota.toEntity())
            }
            finish()
        }
    }

    private fun alterar(notaId: Int) {
        binding.buttonSalvar.text = "Alterar"

        binding.buttonSalvar.setOnClickListener {
            if (binding.editTextConteudo.text.toString().isNotBlank()) {
                val campoTitulo = binding.editTextTitulo.text.toString() ?: "Sem titulo"
                val campoImagens = listaDeUrls.joinToString(",")
                MyApplication.database?.dao?.atualizarNota(
                    notaId,
                    binding.editTextConteudo.text.toString(),
                    campoTitulo,
                    campoImagens)
            }
            finish()
            Log.d("MinhaTag", "Teste das notas: ${MyApplication.database?.dao?.getNotas()}")
        }
    }

    private fun exibirInformacoesNota(nota: Notas?) {
        if (nota != null) {
            // Exiba as informações da nota nos elementos da tela do formulário
            binding.editTextTitulo.setText(nota.titulo)
            binding.editTextConteudo.setText(nota.conteudo)
            // Exiba as imagens, você precisa implementar a lógica aqui
            if (nota.imagens != null && nota.imagens != "Sem Imagens"){
                listaDeUrls = nota.imagens!!.split(", ").toMutableList()
                if (listaDeUrls.isNotEmpty()){
                    exibirTodasAsImagens()
                }
            }
        }
    }

    private fun exibirTodasAsImagens() {
        // Limpe as imagens antigas
        binding.imageView.setImageDrawable(null)

        // Adicione novas ImageView para cada imagem na lista
        for (url in listaDeUrls) {
            binding.imageView.load(url) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground_placeholder) // Recurso de placeholder enquanto a imagem carrega
            }
        }
    }

}