package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.databinding.ViewCentersFragmentBinding

class AllCentersFragment : Fragment() {

    private lateinit var binding: ViewCentersFragmentBinding

    companion object {
        fun newInstance() = AllCentersFragment()
    }

    private val viewModel: AllCentersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewCentersFragmentBinding.inflate(inflater)
        return binding.root
    }

}