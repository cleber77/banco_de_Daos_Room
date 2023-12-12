package com.cleber.agendacontator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cleber.agendacontator.Dao.UsuarioDao
import com.cleber.agendacontator.databinding.ActivityCadrastraUsuarioBinding
import com.cleber.agendacontator.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadrastraUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityCadrastraUsuarioBinding
    private lateinit var usuarioDao: UsuarioDao
    private  val listaUsuarios : MutableList<Usuario> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadrastraUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.btcadastrarAqui.setOnClickListener{

                CoroutineScope(Dispatchers.IO).launch {
                val nome = binding.editnome.text.toString()
                val sobrenome = binding.editsobrenome.text.toString()
                val idade = binding.editIdade.text.toString()
                val celular = binding.editCelular.text.toString()
                val mensagem : Boolean

                if(nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty()){
                    mensagem = false
                }else {
                    mensagem = true
                    cadastra(nome, sobrenome, idade, celular)

                }
                    withContext(Dispatchers.Main) {
                        if (mensagem) {
                            Toast.makeText(
                                applicationContext,
                                "Sucesso ao cadastra ",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()

                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Preenche todos o campos ",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
               }

            }

        }

    private  fun cadastra(nome: String ,sobrenome: String, idade: String, celular: String){

        val usuario = Usuario (nome ,sobrenome ,idade, celular)
        listaUsuarios.add(usuario)
        usuarioDao = AppDatabase.getInstance(this).usuarioDao()
        usuarioDao.inserir(listaUsuarios)

    }
}