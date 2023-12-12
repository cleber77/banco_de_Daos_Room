package com.cleber.agendacontator.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cleber.agendacontator.AtualizarUsuario
import com.cleber.agendacontator.databinding.ContatoItemBinding
import com.cleber.agendacontator.model.Usuario

class ContatoAdapter (private val context: Context, private val listaUsuario:MutableList<Usuario>):
    RecyclerView.Adapter<ContatoAdapter.ContatorViewHolde>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatorViewHolde {
        val itemLista = ContatoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ContatorViewHolde(itemLista)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ContatorViewHolde, position: Int) {
        holder.textName.text= listaUsuario[position].nome
        holder.textSobrenome.text= listaUsuario[position].sobrenome
        holder.textidade.text= listaUsuario[position].idade
        holder.textcelular.text = listaUsuario[position].celular


        holder.btAtualiza.setOnClickListener{
          val intent = Intent(context,AtualizarUsuario::class.java)
            intent.putExtra("nome",listaUsuario[position].nome)
            intent.putExtra("Sobrenome",listaUsuario[position].sobrenome)
            intent.putExtra("idade",listaUsuario[position].idade)
            intent.putExtra("celular",listaUsuario[position].celular)
            intent.putExtra("uid",listaUsuario[position].uid)
            context.startActivity(intent)
        }
    }


    override fun getItemCount() = listaUsuario.size

    inner class ContatorViewHolde(binding: ContatoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val textName = binding.textNome
        val textSobrenome = binding.textsobreNome
        val textidade = binding.textidade
        val textcelular = binding.textcelula
        val btAtualiza = binding.btAtualiza
        val Deletar = binding.btDeletar

    }
}