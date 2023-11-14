package br.com.alura.projectnotas.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.projectnotas.databinding.ActivityFormularioNotaBinding
import br.com.alura.projectnotas.databinding.FormularioNotaDialogBinding

class FormularioNotaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioNotaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAdicionarImagens.setOnClickListener{
            val bindingDialog = FormularioNotaDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this)
                .setView(bindingDialog.root)
                .setPositiveButton("Confirmar"){_,_ ->

                }
                .setNegativeButton("Cancelar"){_,_ ->

                }
                .show()
        }
    }
}