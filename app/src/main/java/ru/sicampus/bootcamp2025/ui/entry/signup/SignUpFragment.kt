package ru.sicampus.bootcamp2025.ui.entry.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.SignupFragmentBinding
import ru.sicampus.bootcamp2025.ui.mainscreen.MainActivity

class SignUpFragment : Fragment(R.layout.signup_fragment) {

    private var _binding: SignupFragmentBinding? = null
    private val binding: SignupFragmentBinding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = SignupFragmentBinding.bind(view)
        val navController = NavHostFragment.findNavController(this)

        binding.toLogin.setOnClickListener {
            navController.navigate(R.id.login_fragment)
        }
        binding.process.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}