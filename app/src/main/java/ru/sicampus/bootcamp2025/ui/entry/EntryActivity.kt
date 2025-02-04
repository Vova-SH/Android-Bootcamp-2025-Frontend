package ru.sicampus.bootcamp2025.ui.entry

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.EnterActivityBinding
import ru.sicampus.bootcamp2025.ui.mainscreen.MainActivity
import ru.sicampus.bootcamp2025.ui.utils.collectWithLifecycle

class EntryActivity : AppCompatActivity(R.layout.enter_activity) {

    private var _binding: EnterActivityBinding? = null
    private val binding: EnterActivityBinding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = EnterActivityBinding.inflate(layoutInflater)

        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is AuthViewModel.State.Loading -> TODO()
                is AuthViewModel.State.Process -> TODO()
            }
        }

        viewModel.action.collectWithLifecycle(this) { action ->
            when (action) {
                is AuthViewModel.Action.OpenApp -> {
                    val intent = Intent(this, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }

        binding.splash.viewTreeObserver.addOnPreDrawListener { viewModel.isReady() }


        setContentView(binding.root)

    }
}