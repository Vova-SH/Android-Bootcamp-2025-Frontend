package ru.sicampus.bootcamp2025.ui.entry.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.SignupFragmentBinding
import ru.sicampus.bootcamp2025.ui.mainscreen.MainActivity
import ru.sicampus.bootcamp2025.ui.utils.collectWithLifecycle
import ru.sicampus.bootcamp2025.ui.utils.getText

class SignUpFragment : Fragment(R.layout.signup_fragment) {

    private var _binding: SignupFragmentBinding? = null
    private val binding: SignupFragmentBinding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels<SignUpViewModel> { SignUpViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = SignupFragmentBinding.bind(view)
        val navController = NavHostFragment.findNavController(this)

        binding.repeatPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.wrapRepeatPassword.isErrorEnabled = p0 != binding.password
                binding.wrapRepeatPassword.error =
                    getText(R.string.password_does_not_matches, requireContext())
            }
        })

        viewModel.state.collectWithLifecycle(this) { state ->
            binding.wrapRepeatPassword.isErrorEnabled = state is SignUpViewModel.State.Error
            when (state) {
                is SignUpViewModel.State.Error -> {
                    binding.wrapRepeatPassword.error = state.errorMessage
                }

                is SignUpViewModel.State.Loading -> {
                    binding.login.isEnabled = false
                    binding.password.isEnabled = false
                    binding.repeatPassword.isEnabled = false
                }

                is SignUpViewModel.State.Waiting -> {
                    binding.login.isEnabled = true
                    binding.password.isEnabled = true
                    binding.repeatPassword.isEnabled = true
                }
            }
        }

        viewModel.action.collectWithLifecycle(this) { action ->
            when (action) {
                SignUpViewModel.Action.OpenApp -> startActivity(
                    Intent(activity, MainActivity::class.java)
                )
            }
        }

        binding.toLogin.setOnClickListener {
            navController.navigate(R.id.login_fragment)
        }

        binding.process.setOnClickListener {
            viewModel.onProcessClick(
                binding.login.text.toString(),
                binding.password.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}