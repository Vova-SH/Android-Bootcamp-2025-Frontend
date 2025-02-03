package ru.sicampus.bootcamp2025.ui.mainscreen.centerinfo

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ViewCenterInfoFragmentBinding

class CenterInfoFragment : Fragment(R.layout.center_item) {
    private var _binding: ViewCenterInfoFragmentBinding? = null
    private val binding: ViewCenterInfoFragmentBinding get() = _binding!!

    private val viewModel: CenterInfoViewModel by viewModels<CenterInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = ViewCenterInfoFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}