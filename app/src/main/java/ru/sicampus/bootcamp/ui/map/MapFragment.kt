package ru.sicampus.bootcamp.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.MapBinding
import ru.sicampus.bootcamp.ui.auth.AuthViewModel
import ru.sicampus.bootcamp.ui.list.ListFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class MapFragment: Fragment(R.layout.map) {
    private var _viewBinding: MapBinding? = null
    private val viewBinding: MapBinding get() = _viewBinding!!

    private val viewModel by viewModels<AuthViewModel> { AuthViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = MapBinding.bind(view)

        viewBinding.listIc.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ListFragment())
                .commitAllowingStateLoss()

            /*  viewModel.clickNext(
                  viewBinding.enterLogin.text.toString(),
                  viewBinding.enterPassword.text.toString(),
              )*/
        }
        viewModel.action.collectWithLifecycle(this) { action ->
            when (action) {
                AuthViewModel.Action.GoToList -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main, ListFragment())
                        .commitAllowingStateLoss()
                }
            }
        }


    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}

