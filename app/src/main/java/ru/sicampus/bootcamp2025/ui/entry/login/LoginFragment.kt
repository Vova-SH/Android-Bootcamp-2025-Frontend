package ru.sicampus.bootcamp2025.ui.entry.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.LoginFragmentBinding

class LoginFragment : Fragment(R.layout.login_fragment) {
    private var _binding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = LoginFragmentBinding.bind(view)
        val navController = NavHostFragment.findNavController(this)

        binding.toSignup.setOnClickListener {
            navController.navigate(R.id.signup_fragment)
        }
        binding.process.setOnClickListener {
            // TODO: Add checking and caching credentials
            startActivity(Intent(activity, LoginFragment::class.java))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}