package ru.sicampus.bootcamp2025.ui.entry.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.sicampus.bootcamp2025.MainActivity
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentRegisterBinding
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private var _viewBinding : FragmentRegisterBinding? = null
    private val viewBinding : FragmentRegisterBinding get() = _viewBinding!!

    private val viewModel by viewModels<RegisterViewModel> { RegisterViewModel.Factory }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentRegisterBinding.bind(view)

        viewBinding.goToAuthButton.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_auth)
        }

        viewBinding.signInButton.setOnClickListener {
            val email = viewBinding.email.text.toString()
            val password = viewBinding.password.text.toString()
            val name = viewBinding.name.text.toString()
            if (!isValidName(name)) {
                viewBinding.errorText.text = getString(R.string.error_name_no_valid)
                viewBinding.errorText.visibility = View.VISIBLE
            }
            else if (!isValidEmail(email)) {
                viewBinding.errorText.text = getString(R.string.error_email_no_valid)
                viewBinding.errorText.visibility = View.VISIBLE
            }
            else if (!isValidPassword(password)) {
                viewBinding.errorText.text = getString(R.string.error_password_no_valid)
                viewBinding.errorText.visibility = View.VISIBLE
            }
            else {
                viewModel.register(email, password, name)
                viewBinding.errorText.visibility = View.GONE
            }
        }

        viewModel.state.collectWithLifecycle(this) { state ->
            if (state is RegisterViewModel.State.Show) {
                viewBinding.errorText.text = state.errorText.toString()
                viewBinding.errorText.visibility = View.GONE
            }

        }
        viewModel.navigateToMain.collectWithLifecycle(this) { shouldNavigate ->
            if (shouldNavigate) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

        viewBinding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) { viewModel.changeLogin() }

        })
    }
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password : String) : Boolean {
        return password.length >= 8
    }
    private fun isValidName(name: String): Boolean {
        return (name.all { it.isLetter() || it.isWhitespace() }) and (name.isNotEmpty())
    }


    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}