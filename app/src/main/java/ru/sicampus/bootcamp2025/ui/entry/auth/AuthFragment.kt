package ru.sicampus.bootcamp2025.ui.entry.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.MainActivity
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentAuthBinding
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private var _viewBinding : FragmentAuthBinding? = null
    private val viewBinding : FragmentAuthBinding get() = _viewBinding!!

    private val viewModel by viewModels<AuthViewModel> { AuthViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentAuthBinding.bind(view)

        viewBinding.goToRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_auth_to_register)
        }


        viewBinding.signInButton.setOnClickListener {
            val email = viewBinding.userLogin.text.toString()
            val password = viewBinding.userPassword.text.toString()
            if (!isValidEmail(email)) {
                viewBinding.errorText.text = getString(R.string.error_email_no_valid)
                viewBinding.errorText.visibility = View.VISIBLE
            }
            /*else if (!isValidPassword(password)) {
                viewBinding.errorText.text = getString(R.string.error_password_no_valid)
                viewBinding.errorText.visibility = View.VISIBLE
            }*/
            else {
                viewModel.auth(email, password)
                viewBinding.errorText.visibility = View.GONE
            }
        }

        viewModel.state.collectWithLifecycle(this) { state ->
            if (state is AuthViewModel.State.Show) {
                viewBinding.errorText.text = state.errorText.toString()
                viewBinding.errorText.visibility =
                    if (state.errorText == null) View.GONE else View.VISIBLE
            }
        }

        viewModel.navigateToMain.collectWithLifecycle(viewLifecycleOwner) { userRole ->
            val intent = Intent(requireContext(), MainActivity::class.java).apply {
                putExtra("USER_ROLE", userRole)
            }
            startActivity(intent)
            requireActivity().finish()
        }

        viewBinding.userLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password : String) : Boolean {
        return password.length >= 8
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}