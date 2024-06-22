package app.aplicacion.reto3_basico.core

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.Toast

fun View.showImage(){
    visibility=View.VISIBLE
}

fun View.hideImage() {
    visibility = View.INVISIBLE
}
fun Activity.toasT(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun Activity.cambiarColor(color:Int){
    val windonw=this.window
    windonw.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    windonw.statusBarColor=getColor(color)
}
