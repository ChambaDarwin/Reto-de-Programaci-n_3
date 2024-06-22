package app.aplicacion.reto3_basico.ui.view

import android.app.Dialog
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.aplicacion.reto3_basico.R
import app.aplicacion.reto3_basico.core.cambiarColor
import app.aplicacion.reto3_basico.core.hideImage
import app.aplicacion.reto3_basico.core.showImage
import app.aplicacion.reto3_basico.core.toasT
import app.aplicacion.reto3_basico.data.model.User
import app.aplicacion.reto3_basico.databinding.ActivityMainBinding
import app.aplicacion.reto3_basico.ui.adapter.UserAdapter
import app.aplicacion.reto3_basico.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: UserViewModel
    private lateinit var cadapter:UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(UserViewModel::class.java)
        initRecycler()

        binding.btnAddUser.setOnClickListener { addUser() }
        showAllUser()


    }

    private fun initRecycler() {
        cadapter= UserAdapter()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter=cadapter
            setHasFixedSize(true)
        }
    }
    private fun showImage(lista:List<User>){
        if(lista.isNullOrEmpty()){
            binding.image.showImage()
            cambiarColor(R.color.noList)

        }else{
            binding.image.hideImage()
        }
    }

    private fun addUser(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.add_dialog)
        val name=dialog.findViewById<EditText>(R.id.name)
        val lastName=dialog.findViewById<EditText>(R.id.lastName)
        val nota=dialog.findViewById<EditText>(R.id.nota)
        val add=dialog.findViewById<Button>(R.id.btnAddUserDialog)
        add.setOnClickListener {
            val nameAdd=name.text.toString()
            val lastNameAdd=lastName.text.toString()
            val noteAdd=nota.text.toString()
            val isCorrect=validarCampos(nameAdd,lastNameAdd,noteAdd)
            if(isCorrect){
                val user=User(0,nameAdd,lastNameAdd,noteAdd.toFloat(),"")
                model.insertUser(user)
                toasT("Registro realizado con exito")
            }else{
                toasT("Error: campos requeridos")
            }
        }
        dialog.create()
        dialog.show()

    }
    private  fun validarCampos(name:String,lastName:String,note:String):Boolean{

        if(name.isNullOrEmpty() || lastName.isNullOrEmpty() || note.isNullOrEmpty()){
            return false
        }
        return true

    }
    private fun showAllUser(){
        model.allUser.observe(this, Observer {
            cadapter.diff.submitList(it)
            showImage(it)
        })





    }


}