package br.com.alura.projectnotas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.projectnotas.databinding.CardNoteBinding
import br.com.alura.projectnotas.model.Nota
import coil.load

class NotaAdapter(
    private val context: Context,
    private var notas: List<Nota>
    ):
    RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    class NotaViewHolder(binding: CardNoteBinding): RecyclerView.ViewHolder(binding.root) {
        private val titulo = binding.textTitle
        private val conteudo = binding.textContent
        private val imageView = binding.imageView

        fun vincula(nota: Nota){
            titulo.text = nota.titulo
            conteudo.text = nota.conteudo

            if (nota.imagens != null && nota.imagens != "Sem Imagens"){
                val listaDeUrls = nota.imagens!!.split(", ")
                if (listaDeUrls.isNotEmpty()){
                    imageView.load(listaDeUrls[0])
                }else imageView.visibility = View.GONE
            }else imageView.visibility = View.GONE
        }
    }

    fun atualizarNotas(novasNotas: List<Nota>) {
        notas = novasNotas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val binding = CardNoteBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false)
        return NotaViewHolder(binding)
    }

    override fun getItemCount(): Int = notas.size

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.vincula(nota)
    }
}
