package app.aplicacion.reto3_basico.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "userTable")
class User(
    @PrimaryKey (autoGenerate = true)
    val id:Int,
    val nombre:String,
    val apellido:String,
    val nota:Float,
    var calificacion:String
) {
    init {
        calificacion = when(nota){
           in (1.0..5.0) ->"SS"
            in (5.0..7.0)->"AP"
            in (7.0..9.0)->"NT"
            in(9.0..10.0)->"SB"
            else -> IllegalArgumentException("opcion no valida").toString()


        }
    }

}