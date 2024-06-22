package app.aplicacion.reto3_basico.data.repository

import app.aplicacion.reto3_basico.data.model.User
import app.aplicacion.reto3_basico.data.model.database.UserDao

class UserRepository(val dao: UserDao) {
    suspend fun insertUser(user: User){
        dao.insertUser(user)
    }

    suspend fun deleteUser(user: User){
        dao.deleteUser(user)
    }
    suspend fun updateUser(user: User){
        dao.updateUser(user)
    }
    fun consultarNotasconDni(id:Int)=
        dao.getNotaCalificacion(id)

    fun alumnosSuspendos()=
        dao.mostrarAlumnosSuspendidos()

    fun candidatosAMH()=dao.mostrarAlumnosMH()

    fun alumnosAprobados()=dao.mostrarAlumnosAprobados()

    fun showAllStudents()=dao.mostrarTodosLosAlumnos()



}