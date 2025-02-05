package ru.sicampus.bootcamp2025

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.sicampus.bootcamp2025.databinding.FragmentRegisterFirstBinding
import ru.sicampus.bootcamp2025.databinding.FragmentStarterBinding

class RegisterFirstFragment : Fragment() {
    private var _binding : FragmentRegisterFirstBinding? = null
    private val binding : FragmentRegisterFirstBinding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegisterFirstBinding.bind(view)
        Log.d("StarterFragment", "click")
    }
}