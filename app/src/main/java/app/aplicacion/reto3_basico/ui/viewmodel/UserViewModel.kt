package app.aplicacion.reto3_basico.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.aplicacion.reto3_basico.data.model.User
import app.aplicacion.reto3_basico.data.model.database.UserDatabase
import app.aplicacion.reto3_basico.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel (application: Application):AndroidViewModel(application){

    private val repository:UserRepository
    init {
       val dao=UserDatabase.invoke(application).getDao()
        repository=UserRepository(dao)
    }
    fun insertUser(user:User){
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
}