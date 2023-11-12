package br.com.alura.projectnotas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.projectnotas.databinding.CardNoteBinding
import br.com.alura.projectnotas.model.Nota

class NotaAdapter(
    private val context: Context,
    private val notas: List<Nota>
    ):
    RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    class NotaViewHolder(binding: CardNoteBinding): RecyclerView.ViewHolder(binding.root) {
        private val titulo = binding.textTitle
        private val conteudo = binding.textContent

        fun vincula(nota: Nota){
            titulo.text = nota.titulo
            conteudo.text = nota.conteudo
        }
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