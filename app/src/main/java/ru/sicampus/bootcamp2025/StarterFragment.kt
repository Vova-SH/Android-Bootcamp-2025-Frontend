package ru.sicampus.bootcamp2025

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.sicampus.bootcamp2025.databinding.FragmentStarterBinding

class StarterFragment : Fragment(R.layout.fragment_starter) {
    private var _binding: FragmentStarterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStarterBinding.bind(view)

        binding.registerBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_starterFragment_to_registerFirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}