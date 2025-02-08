package ru.sicampus.bootcamp.ui.centersList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.CenterListBinding
import ru.sicampus.bootcamp.ui.auth.AuthViewModel
import ru.sicampus.bootcamp.ui.list.ListFragment

class CentersListFragment: Fragment(R.layout.center_list) {
    private var _viewBinding: CenterListBinding? = null
    private val viewBinding: CenterListBinding get() = _viewBinding!!

    private val viewModel by viewModels<AuthViewModel> { AuthViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = CenterListBinding.bind(view)

        viewBinding.toVolunteerList.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ListFragment())
                .commitAllowingStateLoss()

            /*  viewModel.clickNext(
                  viewBinding.enterLogin.text.toString(),
                  viewBinding.enterPassword.text.toString(),
              )*/
        }


    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
