package com.cleber.agendacontator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_usuario")
data  class Usuario (

    @ColumnInfo (name = " nome") val nome : String,
    @ColumnInfo (name = " sobrenome") val sobrenome : String,
    @ColumnInfo (name = " idade") val idade : String,
    @ColumnInfo (name = " Celulas") val celular : String,

 ){
     @PrimaryKey(autoGenerate = true)
     var uid : Int = 0
 }