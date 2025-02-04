package ru.sicampus.bootcamp2025.uiList.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.uiList.ListViewModel

class AuthFragment : Fragment(R.layout.authorization)
{
    private var _viewBinding : FragmentAuthBinding? = null
    private val viewBinding : FragmentAuthtBinding get() = _viewBinding!!

    private val viewModel by viewModels<ListViewModel>() { ListViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
