package ru.sicampus.bootcamp2025.ui.profile

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding
import ru.sicampus.bootcamp2025.domain.profile.DataEntity
import ru.sicampus.bootcamp2025.util.collectWithLifecycle

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var currentUser: DataEntity


    private val viewModel: ProfileViewModel by viewModels() { ProfileViewModel.Factory}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val application = requireActivity().application
        sharedPreferences = application.getSharedPreferences("auth_prefs", Application.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        subscribe()
        getProfileData()
    }

    private fun getProfileData() {
        viewModel.getDataByLogin()
        Log.d("ProfileFragment", "getting profile data...")

    }
    fun getSavedLogin(): String? {
        return sharedPreferences.getString("user_login", null)
    }

    private fun subscribe() {
        Log.d("ProfileFragment", "subscribe")
        viewModel.state.collectWithLifecycle(this) {state ->
            Log.d("ProfileFragment", "state show collected")
            if(state is ProfileViewModel.State.Show){
                currentUser = state.item
                Log.d("ProfileFragment", "updated currentUser")
                binding.emailMain.text = state.item.email
                binding.phoneMain.text = state.item.phone
                binding.ctblMain.title = state.item.name
                binding.infoMain.text = state.item.info
            }
            if (state is ProfileViewModel.State.Changed){
                getProfileData()
            }
            binding.error.visibility = if(state is ProfileViewModel.State.Error) View.VISIBLE else View.GONE
        }
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
    private fun getCamera(view: View?) {
        Toast.makeText(activity, "CAMERA", Toast.LENGTH_SHORT).show()
        TODO("Not yet implemented")
    }

    private fun editProfileData() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_edit_profile)
        dialog.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
        }

//        dialog.findViewById<EditText>(R.id.username)?.setText(currentUser.username)
//        dialog.findViewById<EditText>(R.id.email)?.setText(currentUser.email)
//        dialog.findViewById<EditText>(R.id.phone)?.setText(currentUser.phone)
//        dialog.findViewById<EditText>(R.id.info)?.setText(currentUser.info)

        dialog.findViewById<Button>(R.id.saveButton)?.setOnClickListener {
            val updatedUsername = dialog.findViewById<EditText>(R.id.username)?.text.toString()
            val updatedEmail = dialog.findViewById<EditText>(R.id.email)?.text.toString()
            val updatedPhone = dialog.findViewById<EditText>(R.id.phone)?.text.toString()
            val updatedInfo = dialog.findViewById<EditText>(R.id.info)?.text.toString()

            if (updatedUsername.isBlank() || updatedEmail.isBlank() || updatedPhone.isBlank()) {
                Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d("ProfileFragment", "1 sec before error")
            val dataEntity = DataEntity(
                email = updatedEmail,
                info = updatedInfo,
                phone = updatedPhone,
                login = getSavedLogin().toString(),
                name = updatedUsername,
                id = currentUser.id,
            )
            viewModel.changeDataByLogin(
                dataEntity = dataEntity
            )

            dialog.dismiss()
        }
        dialog.show()
    }

    private fun logout(){
        Toast.makeText(activity, "LOGOUT", Toast.LENGTH_SHORT).show()
        TODO("Not yet implemented")
    }
}