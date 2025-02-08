package ru.sicampus.bootcamp2025.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentAuthBinding
import ru.sicampus.bootcamp2025.ui.one.OneCenterFragment
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class AuthFragment: Fragment(R.layout.fragment_auth) {
    private var _viewBinding: FragmentAuthBinding? = null
    private val viewBinding: FragmentAuthBinding get() = _viewBinding!!

    private val viewModel by viewModels<AuthViewModel> { AuthViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentAuthBinding.bind(view)

        viewModel.checkAutoLogin()

        viewBinding.next.setOnClickListener {
            viewModel.clickNext(
                viewBinding.loginEditText.text.toString(),
                viewBinding.passwordEditText.text.toString(),
                viewBinding.nameEditText.text.toString(),
                viewBinding.emailEditText.text.toString()
            )
        }

        viewBinding.loginEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(s: Editable?) {
                viewModel.changeLogin()
            }
        })
        viewModel.action.collectWithLifecycle(this) { action ->
            when (action) {
                AuthViewModel.Action.GoToList -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main, OneCenterFragment())
                        .commitAllowingStateLoss()
                }
            }
        }

        viewModel.state.collectWithLifecycle(this) { state ->
            viewBinding.next.isEnabled = state is AuthViewModel.State.Show
            viewBinding.nameInputLayout.isEnabled = state is AuthViewModel.State.Show
            viewBinding.emailInputLayout.isEnabled = state is AuthViewModel.State.Show
            viewBinding.passwordInputLayout.isEnabled = state is AuthViewModel.State.Show

            if (state is AuthViewModel.State.Show) {
                viewBinding.title.text = state.titleText
                viewBinding.next.text = state.buttonText
                viewBinding.errorMessage.text = state.errorText
                viewBinding.errorMessage.visibility =
                    if (state.errorText != null) View.VISIBLE else View.GONE
                viewBinding.passwordInputLayout.visibility =
                    if (state.showPassword) View.VISIBLE else View.GONE

            }

        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
