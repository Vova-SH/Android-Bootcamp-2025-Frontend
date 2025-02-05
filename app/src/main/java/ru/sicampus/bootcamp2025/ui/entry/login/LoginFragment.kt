package ru.sicampus.bootcamp2025.ui.entry.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.LoginFragmentBinding
import ru.sicampus.bootcamp2025.ui.mainscreen.MainActivity
import ru.sicampus.bootcamp2025.ui.utils.collectWithLifecycle

class LoginFragment : Fragment(R.layout.login_fragment) {
    private var _binding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding get() = _binding!!
    private var _navController: NavController? = null
    private val navController: NavController get() = _navController!!

    private val viewModel: LoginViewModel by viewModels<LoginViewModel> { LoginViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = LoginFragmentBinding.bind(view)
        _navController = NavHostFragment.findNavController(this)

        viewModel.state.collectWithLifecycle(this) { state ->
            binding.passwordWrap.isErrorEnabled = state is LoginViewModel.State.Error
            when (state) {
                is LoginViewModel.State.Error -> {
                    binding.passwordWrap.error = state.errorMessage
                }
                is LoginViewModel.State.Loading -> {
                    binding.login.isEnabled = false
                    binding.password.isEnabled = false
                }
                is LoginViewModel.State.Waiting -> {
                    binding.login.isEnabled = true
                    binding.password.isEnabled = true
                }
            }
        }

        viewModel.action.collectWithLifecycle(this) { action ->
            when (action) {
                LoginViewModel.Action.OpenApp -> startActivity(
                    Intent(activity, MainActivity::class.java)
                )

                LoginViewModel.Action.GoToSignUp -> goToSignUp()
            }

        }
        binding.toSignup.setOnClickListener { goToSignUp() }

        binding.process.setOnClickListener {
            viewModel.onProcessClick(
                binding.login.text.toString(),
                binding.password.text.toString()
            )
        }
    }

    private fun goToSignUp() {
        navController.navigate(R.id.signup_fragment)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}