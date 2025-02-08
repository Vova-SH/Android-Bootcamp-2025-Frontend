package ru.sicampus.bootcamp2025.ui.mainscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentMainScreenBinding
import ru.sicampus.bootcamp2025.ui.list.ListFragment
import ru.sicampus.bootcamp2025.ui.main.MainActivity

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private var _viewBinding: FragmentMainScreenBinding? = null
    private val viewBinding: FragmentMainScreenBinding get() = _viewBinding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentMainScreenBinding.bind(view)

        (activity as? MainActivity)?.showBottomNavigation()

        viewBinding.fakeSearchButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ListFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}