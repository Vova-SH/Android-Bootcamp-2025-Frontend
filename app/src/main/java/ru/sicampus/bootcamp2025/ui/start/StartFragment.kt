package ru.sicampus.bootcamp2025.ui.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentStartBinding
import ru.sicampus.bootcamp2025.ui.auth.AuthFragment

class StartFragment : Fragment(R.layout.fragment_start) {
    private var _viewBinding: FragmentStartBinding? = null
    private val viewBinding: FragmentStartBinding get() = _viewBinding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentStartBinding.bind(view)

        viewBinding.registerButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, AuthFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}