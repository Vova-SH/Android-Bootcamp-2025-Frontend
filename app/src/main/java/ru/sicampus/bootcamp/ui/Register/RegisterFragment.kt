package ru.sicampus.bootcamp.ui.Register

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.RegistrationBinding
import ru.sicampus.bootcamp.ui.auth.AuthFragment
import ru.sicampus.bootcamp.ui.auth.AuthViewModel
import ru.sicampus.bootcamp.ui.list.RegisterViewModel
import ru.sicampus.bootcamp.ui.map.MapFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class RegisterFragment : Fragment(R.layout.registration) {
    private var _viewBinding: RegistrationBinding? = null
    private val viewBinding: RegistrationBinding get() = _viewBinding!!

    private val viewModel by viewModels<RegisterViewModel> { RegisterViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = RegistrationBinding.bind(view)
          viewBinding.logInBtn.setOnClickListener {
              val l: List<String> = viewBinding.enterName.text.split(" ");
            viewModel.clickNext(
                viewBinding.enterEmailReg3.text.toString(),
                viewBinding.enterPasswordReg.text.toString(),
                viewBinding.enterEmailReg.text.toString(),
                l[0],
                l[1],
                l[2],
                viewBinding.enterPhone.text.toString(),
                viewBinding.enterPasswordReg.text.toString(),
                //"https://plus.unsplash",
                ""
            )
              Log.d("logL", "${l[0]}"+ " " + "${l[1]}" + " " + "${l[2]}")
       }

        viewModel.action.collectWithLifecycle(this) { action ->
            when (action) {
                RegisterViewModel.Action.GoToAuth -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main, AuthFragment())
                        .commitAllowingStateLoss()
                }

                else -> {

                }
            }

        }
    }
    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()

    }
}

