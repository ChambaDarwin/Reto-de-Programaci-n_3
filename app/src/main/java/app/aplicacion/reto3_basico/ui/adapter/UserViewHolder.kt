package app.aplicacion.reto3_basico.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.aplicacion.reto3_basico.data.model.User
import app.aplicacion.reto3_basico.databinding.ItemRecyclerBinding

class UserViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding=ItemRecyclerBinding.bind(view)

    fun render(user:User,listener:((User)->Unit)?){
        binding.nombre.text=user.nombre
        binding.apellido.text=user.apellido
        binding.nota.text=user.nota.toString()
        binding.calificacion.text=user.calificacion
        itemView.setOnClickListener {
            listener?.invoke(user)
        }
    }
}