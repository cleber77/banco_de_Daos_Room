package com.cleber.agendacontator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.cleber.agendacontator.Dao.UsuarioDao
import com.cleber.agendacontator.adapter.ContatoAdapter
import com.cleber.agendacontator.databinding.ActivityMainBinding
import com.cleber.agendacontator.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var contatorAdapter : ContatoAdapter
    private  val _listaUsuario = MutableLiveData <MutableList<Usuario>>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            getContatos()

            withContext(Dispatchers.Main){

                _listaUsuario.observe(this@MainActivity){listaUsuario ->
                    val recyclerViewContatos = binding.recycleViewContato
                    recyclerViewContatos.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerViewContatos.setHasFixedSize(true)
                    contatorAdapter = ContatoAdapter(this@MainActivity, listaUsuario )
                    recyclerViewContatos.adapter =contatorAdapter
                    contatorAdapter.notifyDataSetChanged()
                }
            }
        }

        binding.btcadastrar.setOnClickListener{
           val navegaTelaCadastro = Intent(this,CadrastraUsuario::class.java)
            startActivity(navegaTelaCadastro)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            getContatos()

            withContext(Dispatchers.Main){

                _listaUsuario.observe(this@MainActivity){listaUsuario ->
                    val recyclerViewContatos = binding.recycleViewContato
                    recyclerViewContatos.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerViewContatos.setHasFixedSize(true)
                    contatorAdapter = ContatoAdapter(this@MainActivity, listaUsuario )
                    recyclerViewContatos.adapter =contatorAdapter
                    contatorAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    private fun  getContatos(){

        usuarioDao = AppDatabase.getInstance(this).usuarioDao()
        val listaUsuario : MutableList<Usuario> = usuarioDao.get()
        _listaUsuario.postValue(listaUsuario)

    }
}