package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sicampus.bootcamp2025.R

class AllCentersFragment : Fragment() {

    companion object {
        fun newInstance() = AllCentersFragment()
    }

    private val viewModel: AllCentersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.view_centers_fragment, container, false)
    }
}