package ru.sicampus.bootcamp2025.ui.mainscreen.allusers

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.UnemployeedVolunteersFragmentBinding

class AllUsersFragment : Fragment(R.layout.unemployeed_volunteers_fragment) {
    private var _binding: UnemployeedVolunteersFragmentBinding? = null
    private val binding: UnemployeedVolunteersFragmentBinding get() = _binding!!

    private val viewModel: AllUsersViewModel by viewModels<AllUsersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = UnemployeedVolunteersFragmentBinding.bind(view)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}