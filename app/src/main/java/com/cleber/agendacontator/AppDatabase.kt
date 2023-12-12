package com.cleber.agendacontator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cleber.agendacontator.Dao.UsuarioDao
import com.cleber.agendacontator.model.Usuario


@Database(entities = [Usuario::class], version = 1)
 abstract class AppDatabase: RoomDatabase() {

     abstract  fun usuarioDao(): UsuarioDao
     companion object{
     private  const val DATABASE_NOME = "DB_USUARIO"

         @Volatile
         private var INSTANCE : AppDatabase?  = null

         fun getInstance(context: Context): AppDatabase{
             return INSTANCE ?: kotlin.synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     AppDatabase::class.java,
                     DATABASE_NOME
                         ).build()

                             INSTANCE = instance
                             instance
             }
         }
     }
}