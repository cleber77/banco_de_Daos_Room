package com.cleber.agendacontator.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cleber.agendacontator.model.Usuario


@Dao
interface UsuarioDao {

    @Insert
    fun inserir(listaUsuario: MutableList<Usuario>)

    @Query("SELECT * FROM TABELA_USUARIO ORDER BY ` nome` ASC")
    fun get():MutableList<Usuario>


    @Query("UPDATE tabela_usuario SET nome = :novoNome, sobrenome = :novoSobrenome,idade = :novoIdade, celular = :novoCelular  WHERE  uid = :id")
    fun Atualiza(id : Int , novoNome :String, novoSobrenome :String, novoIdade :String, novoCelular :String )

}