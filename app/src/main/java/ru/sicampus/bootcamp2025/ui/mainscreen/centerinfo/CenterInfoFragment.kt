package ru.sicampus.bootcamp2025.ui.mainscreen.centerinfo

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sicampus.bootcamp2025.R

class CenterInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CenterInfoFragment()
    }

    private val viewModel: CenterInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.view_center_info_fragment, container, false)
    }
}