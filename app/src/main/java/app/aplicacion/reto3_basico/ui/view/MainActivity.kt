package app.aplicacion.reto3_basico.ui.view

import android.app.Dialog
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.aplicacion.reto3_basico.R
import app.aplicacion.reto3_basico.core.cambiarColor
import app.aplicacion.reto3_basico.core.hideImage
import app.aplicacion.reto3_basico.core.showImage
import app.aplicacion.reto3_basico.core.toasT
import app.aplicacion.reto3_basico.data.delete.DeleteUser
import app.aplicacion.reto3_basico.data.model.User
import app.aplicacion.reto3_basico.databinding.ActivityMainBinding
import app.aplicacion.reto3_basico.ui.adapter.UserAdapter
import app.aplicacion.reto3_basico.ui.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: UserViewModel
    private lateinit var cadapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(UserViewModel::class.java)
        initRecycler()
        binding.btnAddUser.setOnClickListener { addUser() }
        showAllUser()
        deleteUser()
        editUser()
        setSupportActionBar(binding.toolBar)
    }

    private fun initRecycler() {
        cadapter = UserAdapter()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cadapter
            setHasFixedSize(true)
        }
    }

    private fun showImage(lista: List<User>) {
        if (lista.isNullOrEmpty()) {
            binding.image.showImage()
            cambiarColor(R.color.noList)

        } else {
            binding.image.hideImage()
        }
    }

    private fun addUser(user: User? = null) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_dialog)
        val name = dialog.findViewById<EditText>(R.id.name)
        val lastName = dialog.findViewById<EditText>(R.id.lastName)
        val nota = dialog.findViewById<EditText>(R.id.nota)
        val add = dialog.findViewById<Button>(R.id.btnAddUserDialog)
        val title=dialog.findViewById<TextView>(R.id.title)

        if (user == null) {
            add.setOnClickListener {
                val nameAdd = name.text.toString()
                val lastNameAdd = lastName.text.toString()
                val noteAdd = nota.text.toString()
                val isCorrect = validarCampos(nameAdd, lastNameAdd, noteAdd)
                if (isCorrect) {
                    val userInsert = User(0, nameAdd, lastNameAdd, noteAdd.toFloat(), "")
                    model.insertUser(userInsert)
                    toasT("Registro realizado con exito")
                    dialog.dismiss()
                } else {
                    toasT("Error: campos requeridos")
                }
            }
        }else{
            title.setText("Update User")
            add.setText("Update")

            add.setOnClickListener {
                val nameAdd = name.text.toString()
                val lastNameAdd = lastName.text.toString()
                val noteAdd = nota.text.toString()
                val isCorrect = validarCampos(nameAdd, lastNameAdd, noteAdd)
                if (isCorrect) {
                    val newUser = User(user.id, nameAdd, lastNameAdd, noteAdd.toFloat(), "")
                    model.updateUser(newUser)
                    toasT("Registro modificado con exito")
                    dialog.dismiss()
                } else {
                    toasT("Error: campos requeridos")
                }
            }
        }

        dialog.create()
        dialog.show()

    }

    private fun validarCampos(name: String, lastName: String, note: String): Boolean {

        if (name.isNullOrEmpty() || lastName.isNullOrEmpty() || note.isNullOrEmpty()) {
            return false
        }
        return true

    }

    private fun showAllUser() {
        model.allUser.observe(this, Observer {
            cadapter.diff.submitList(it)
            showImage(it)
        })

    }

    private fun deleteUser() {
        val user = object : DeleteUser() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val objectUser = cadapter.diff.currentList[position]
                model.deleteUser(objectUser)
                Snackbar.make(binding.root, "registro eliminado con exito", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("deshacer") {
                            model.insertUser(objectUser)
                        }
                    }.show()
            }

        }
        ItemTouchHelper(user).apply {
            attachToRecyclerView(binding.recycler)
        }
    }

    private fun editUser() {
        cadapter.setOnClickUserSelected {
            addUser(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.alumnosSuspensos->{
                model.alumnosSuspensos.observe(this, Observer {
                    cadapter.diff.submitList(it)
                })
                return true
            }
            R.id.alumnosAprobados->{
                model.alumnosAprobados.observe(this, Observer {
                    cadapter.diff.submitList(it)
                })
                return true
            }
            R.id.buscarPorId->{
                val dialog=Dialog(this)
                dialog.setContentView(R.layout.search_dialog)
                val numero=dialog.findViewById<EditText>(R.id.etBuscar)
                val boton=dialog.findViewById<Button>(R.id.btnBuscar)


                boton.setOnClickListener {
                    val myNumber=numero.text.toString()
                    val listaSize=cadapter.diff.currentList.size
                    if(!myNumber.isNullOrEmpty()){
                        model.buscarPorDni(myNumber.toInt()).observe(this, Observer {
                            cadapter.diff.submitList(it)
                        })
                        dialog.dismiss()

                    }else{
                        toasT("ingresa un id valido")
                    }
                }
                dialog.create()
                dialog.show()


            }
        }
        return super.onOptionsItemSelected(item)
    }




}