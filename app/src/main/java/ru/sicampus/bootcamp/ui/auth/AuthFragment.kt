package ru.sicampus.bootcamp.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.AuthorizationBinding
import ru.sicampus.bootcamp.ui.map.MapFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class AuthFragment: Fragment(R.layout.authorization) {
    private var _viewBinding: AuthorizationBinding? = null
    private val viewBinding: AuthorizationBinding get() = _viewBinding!!

    private val viewModel by viewModels<AuthViewModel> { AuthViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = AuthorizationBinding.bind(view)

        viewBinding.logInBtn.setOnClickListener {
          viewModel.clickNext(
                viewBinding.enterLogin.text.toString(),
                viewBinding.enterPassword.text.toString(),
            )
        }

        viewBinding.enterLogin.addTextChangedListener(object : TextWatcher {
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
                        .replace(R.id.main, MapFragment())
                        .commitAllowingStateLoss()
                }
            }
        }

        viewModel.state.collectWithLifecycle(this) { state ->
            viewBinding.logInBtn.isEnabled = state is AuthViewModel.State.Show
            viewBinding.enterLogin.isEnabled = state is AuthViewModel.State.Show
            viewBinding.enterPassword.isEnabled = state is AuthViewModel.State.Show

            if (state is AuthViewModel.State.Show) {
                viewBinding.welcome.text = state.titleText
                viewBinding.logInBtn.text = state.buttonText
               // viewBinding.error.text = state.errorText
               // viewBinding.error.visibility =
                   // if (state.errorText != null) View.VISIBLE else View.GONE

            }

        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
