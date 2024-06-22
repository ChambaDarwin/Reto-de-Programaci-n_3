package app.aplicacion.reto3_basico.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import app.aplicacion.reto3_basico.R
import app.aplicacion.reto3_basico.databinding.ActivityMainBinding
import app.aplicacion.reto3_basico.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model=ViewModelProvider(this).get(UserViewModel::class.java)
    }
}