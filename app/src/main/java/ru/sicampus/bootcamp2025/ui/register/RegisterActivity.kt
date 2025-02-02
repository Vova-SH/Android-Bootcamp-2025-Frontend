package ru.sicampus.bootcamp2025.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.sicampus.bootcamp2025.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding
    }
}