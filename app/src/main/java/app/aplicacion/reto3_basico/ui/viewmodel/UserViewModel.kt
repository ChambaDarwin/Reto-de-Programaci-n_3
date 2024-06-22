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
    init {
        val dao = UserDatabase.invoke(application).getDao()
        repository = UserRepository(dao)
        allUser=repository.showAllStudents()


    }
    val listEmpty=MutableLiveData<Boolean> ()

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)

        }
    }
    fun mostrarImagen(estaVacio:Boolean){
        listEmpty.value = estaVacio
    }



}