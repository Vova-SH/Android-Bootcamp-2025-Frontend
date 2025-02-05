package ru.sicampus.bootcamp2025.uiList.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.AuthorizationBinding
import ru.sicampus.bootcamp2025.util.collectionWithLifecycle

class AuthFragment : Fragment(R.layout.authorization)
{
    private var _viewBinding : AuthorizationBinding? = null
    private val viewBinding : AuthorizationBinding get() = _viewBinding!!

    private val viewModel by viewModels<AuthViewModel> { AuthViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = AuthorizationBinding.bind(view)

        viewBinding.logInBtn.setOnClickListener( ){
            viewModel.clickNext(viewBinding.enterLogin.text.toString(), viewBinding.enterPassword.text.toString())
        }

        viewBinding.enterLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) = Unit
        })

        viewModel.state.collectionWithLifecycle(this){ state ->
            viewBinding.logInBtn.isEnabled = state is AuthViewModel.State.Show
            viewBinding.enterLogin.isEnabled = state is AuthViewModel.State.Show
            viewBinding.enterPassword.isEnabled = state is AuthViewModel.State.Show

            if(state is AuthViewModel.State.Show){
                viewBinding.logInBtn.text = state.titleText
                //TODO, error and title texts

                if(state.errorText != null) View.VISIBLE else View.GONE
                viewBinding.enterPassword.visibility = if(state.showPassword) View.VISIBLE else View.GONE
            }
        }
    }


    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

}
