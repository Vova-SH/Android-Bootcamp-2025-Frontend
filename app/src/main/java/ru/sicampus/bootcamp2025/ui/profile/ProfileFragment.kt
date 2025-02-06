package ru.sicampus.bootcamp2025.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileVIewModel by viewModels()
    private fun getCamera(view: View?) {
        Toast.makeText(activity, "CAMERA", Toast.LENGTH_SHORT).show()
        TODO("Not yet implemented")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
//        getProfileData()
    }

    private fun getProfileData() {
        TODO()
    }


    private fun initButtons() {
        binding.fabMain.setOnClickListener(this::getCamera)
        binding.mtbMain.setNavigationOnClickListener {
            Toast.makeText(activity, "BACK", Toast.LENGTH_SHORT).show()
        }
        binding.mtbMain.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.logout -> {
                    logout()
                    true
                }
                R.id.edit -> {
                    editProfileData()
                    true
                }
                else -> {false}
            }
        }
    }

    private fun editProfileData(){
        Toast.makeText(activity, "EDIT", Toast.LENGTH_SHORT).show()
        TODO()
    }

    private fun logout(){
        Toast.makeText(activity, "LOGOUT", Toast.LENGTH_SHORT).show()
        TODO("Not yet implemented")
    }
}