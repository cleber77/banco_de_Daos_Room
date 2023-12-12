package com.cleber.agendacontator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.cleber.agendacontator.Dao.UsuarioDao
import com.cleber.agendacontator.databinding.ActivityAtualizarUsuarioBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AtualizarUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarUsuarioBinding
    private lateinit var usuarioDao : UsuarioDao

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val  nomeRecuperado = intent.extras?.getString("nome")
        val  SobrenomeRecuperado = intent.extras?.getString("sobrenome")
        val  idadeRecuperado = intent.extras?.getString("idade")
        val  celularRecuperado = intent.extras?.getString("celular")
        val  uid = intent.extras!!.getInt("uid")

          binding.editnome.setText(nomeRecuperado)
          binding.editsobrenome.setText(SobrenomeRecuperado)
          binding.editIdade.setText(idadeRecuperado)
          binding.editCelular.setText(celularRecuperado)


        binding.btAtualizar.setOnClickListener{
          CoroutineScope(Dispatchers.IO).launch{

              val nome  = binding.editnome.text.toString()
              val Sobrenome = binding.editsobrenome.text.toString()
              val idade  = binding.editIdade.text.toString()
              val celular = binding.editCelular.text.toString()
              val mensagem : Boolean

              if (nome.isEmpty() || Sobrenome.isEmpty() || idade.isEmpty()  || celular.isEmpty()) {
                  mensagem = false
              }else{
                  mensagem = true
                  atualizaContato(uid , nome,Sobrenome,idade,celular )
              }
              withContext(Dispatchers.Main){
                  if (mensagem){
                      Toast.makeText(this@AtualizarUsuario,"Sucesso ao atualiza ",Toast.LENGTH_SHORT).show()
                      finish()
                  }else {
                      Toast.makeText(this@AtualizarUsuario,"Preencha tudo os campos",Toast.LENGTH_SHORT).show()

                  }
              }
           }
        }
    }
    private  fun atualizaContato(uid: Int,nome: String, sobremone: String, idade : String, celular :String){
        usuarioDao = AppDatabase.getInstance(this).usuarioDao()
        usuarioDao.Atualiza(uid,nome,sobremone,idade,celular)


    }
}