package app.aplicacion.reto3_basico.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.aplicacion.reto3_basico.data.model.User
import app.aplicacion.reto3_basico.data.model.database.UserDatabase
import app.aplicacion.reto3_basico.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    val allUser :LiveData<List<User>>
    val alumnosSuspensos:LiveData<List<User>>
    val alumnosAprobados:LiveData<List<User>>
    val candidatosMh:LiveData<List<User>>


    init {
        val dao = UserDatabase.invoke(application).getDao()
        repository = UserRepository(dao)
        allUser=repository.showAllStudents()
        alumnosSuspensos=repository.alumnosSuspendos()
        alumnosAprobados=repository.alumnosAprobados()
        candidatosMh=repository.candidatosAMH()

    }
    fun buscarPorDni(id:Int):LiveData<List<User>>{
        return repository.consultarNotasconDni(id)
    }



    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)

        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
    fun  updateUser(user:User){
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }



}