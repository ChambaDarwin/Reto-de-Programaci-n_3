package app.aplicacion.reto3_basico.data.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.aplicacion.reto3_basico.data.model.User
import java.util.concurrent.locks.Lock

@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class UserDatabase:RoomDatabase()  {

    abstract fun getDao():UserDao

    companion object{
        @Volatile
        private var instance:UserDatabase?=null
        private val LOCK=Any()
        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createInstanceData(context).also{ instance = it}
        }
        private fun createInstanceData(context: Context)=
            Room.databaseBuilder(context.applicationContext,
            UserDatabase::class.java,
            "dataUser").fallbackToDestructiveMigration().build()
        }

}