package app.aplicacion.reto3_basico.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.aplicacion.reto3_basico.R
import app.aplicacion.reto3_basico.data.model.User

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private val user = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.equals(newItem)
        }

    }
    val diff = AsyncListDiffer(this, user)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        )
    }

    override fun getItemCount() = diff.currentList.size
    private var userSelected: ((User) -> Unit)? = null

    fun setOnClickUserSelected(listener: ((User) -> Unit)?) {
        userSelected = listener
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.render(diff.currentList[position],userSelected)
    }

}