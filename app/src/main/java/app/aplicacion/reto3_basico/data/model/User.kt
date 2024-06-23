package app.aplicacion.reto3_basico.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val apellido: String,
    val nota: Float,
    var calificacion: String
) {
    init {
        calificacion = when {
            nota < 5 -> "SS"
            nota in (5.0..6.9) -> "AP"
            nota in (7.0..8.9) -> "NT"
            nota in (9.0 .. 9.9) -> "SB"
            nota >= 10.0 -> "MH"
            else -> IllegalArgumentException("opcion no valida").toString()


        }
    }

}