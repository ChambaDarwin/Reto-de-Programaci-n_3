package app.aplicacion.reto3_basico.data.delete

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class DeleteUser :ItemTouchHelper.Callback(){

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val position=ItemTouchHelper.LEFT or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or
                ItemTouchHelper.DOWN
        return makeMovementFlags(0,position)
    }
}