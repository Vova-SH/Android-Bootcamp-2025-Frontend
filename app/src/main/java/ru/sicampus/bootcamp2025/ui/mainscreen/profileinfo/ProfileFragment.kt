package ru.sicampus.bootcamp2025.ui.mainscreen.profileinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ViewProfileFragmentBinding

class ProfileFragment : Fragment(R.layout.view_profile_fragment) {

    private var _binding: ViewProfileFragmentBinding? = null
    private val binding: ViewProfileFragmentBinding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = ViewProfileFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}