package app.aplicacion.reto3_basico.data.model.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.aplicacion.reto3_basico.data.model.User
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from userTable where id = :idUser")
    fun getNotaCalificacion(idUser: Int):LiveData<List<User>>

    @Query("select * from userTable where nota<7.0")
    fun mostrarAlumnosSuspendidos():LiveData<List<User>>

    @Query("select * from userTable where nota>7.0")
    fun mostrarAlumnosAprobados():LiveData<List<User>>

    @Query("select * from userTable where nota=10.0")
    fun mostrarAlumnosMH():LiveData<List<User>>

    @Query("select * from userTable ")
   fun mostrarTodosLosAlumnos(): LiveData<List<User>>


}