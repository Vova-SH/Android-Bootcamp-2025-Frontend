package ru.sicampus.bootcamp2025.ui.profile

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding
import ru.sicampus.bootcamp2025.domain.profile.PersonEntity
import ru.sicampus.bootcamp2025.ui.auth.AuthFragment
import ru.sicampus.bootcamp2025.ui.map.MapFragment
import ru.sicampus.bootcamp2025.util.collectWithLifecycle

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var currentUser: PersonEntity
    private lateinit var userAvatarUri: Uri

//    private val cropImageActivity: ActivityResultLauncher<CropImageContractOptions> =
//        registerForActivityResult(CropImageContract()) { result ->
//            userAvatarUri = result.uriContent!!
//            setImageToAvatar(userAvatarUri)
//        }
    private fun setImageToAvatar(uri: Uri) {
        Glide.with(requireContext())
            .load(uri)
            .into(binding.avatar)
    }

    private val viewModel: ProfileViewModel by viewModels() { ProfileViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        viewModel.state.collectWithLifecycle(this) { state ->
            Log.d("ProfileFragment", "state show collected")
            if (state is ProfileViewModel.State.Show) {
                currentUser = state.item
                Log.d("ProfileFragment", "updated currentUser")
                binding.emailMain.text = state.item.email
                binding.departmentName.text = state.item.departmentName

                if (state.item.phone == "") {
                    binding.phoneMain.text = "Не указан"
                } else {
                    binding.phoneMain.text = state.item.phone
                }
                binding.ctblMain.text = state.item.name
                if (state.item.phone == "") {
                    binding.infoMain.text = "Не указана"
                } else {
                    binding.infoMain.text = state.item.info
                }

            }
            if (state is ProfileViewModel.State.Changed) {
                getProfileData()
            }
            binding.error.visibility =
                if (state is ProfileViewModel.State.Error) View.VISIBLE else View.GONE
        }

    }
    fun subscribe2Logout(){
        viewModel.action.collectWithLifecycle(this) { action ->
            if(action is ProfileViewModel.Action.GotoAuth){
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main, AuthFragment())
                    .commitAllowingStateLoss()
            }
        }
    }
    fun subscribe2CropResult(){

    }

    private fun initButtons() {
        binding.fabMain.setOnClickListener(this::getCamera)
        binding.mtbMain.setNavigationOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()  // TODO
            transaction.replace(R.id.main, MapFragment())
            transaction.commit()
        }
        binding.materialButton.setOnClickListener(this::detach)

        binding.mtbMain.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.logout -> {
                    logout()
                    true
                }

                R.id.edit -> {
                    editProfileData()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }


    private fun getCamera(view: View?) {  // TODO
        Toast.makeText(activity, "CAMERA", Toast.LENGTH_SHORT).show()
        startCrop()
    }

    private fun startCrop() {
        val options = CropImageOptions().apply {
            imageSourceIncludeCamera = false
            imageSourceIncludeGallery = true
            aspectRatioX = 1
            aspectRatioY = 1
            cropShape = CropImageView.CropShape.RECTANGLE
            fixAspectRatio = true
            showCropOverlay = true
            outputCompressFormat = Bitmap.CompressFormat.PNG
        }

        val cropOptions = CropImageContractOptions(null, options)
        cropImageActivity.launch(cropOptions)
    }
    private val cropImageActivity: ActivityResultLauncher<CropImageContractOptions> =
        registerForActivityResult(CropImageContract()) { result ->
            if (result.isSuccessful) {
                userAvatarUri = result.uriContent!!
                setImageToAvatar(userAvatarUri)
            } else {
                Toast.makeText(activity, "Обрезка отменена", Toast.LENGTH_SHORT).show()
            }
        }
    private fun detach(view: View?){
        var personEntity = currentUser.copy()
        personEntity.departmentName = "Не указан"
    viewModel.changeDataByLogin(PersonEntity = personEntity)
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
                Toast.makeText(
                    requireContext(),
                    "Please fill all required fields",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            Log.d("ProfileFragment", "1 sec before error")
            val personEntity = PersonEntity(
                email = updatedEmail,
                info = updatedInfo,
                phone = updatedPhone,
                login = getSavedLogin().toString(),
                name = updatedUsername,
                id = currentUser.id,
                departmentName = currentUser.departmentName,
            )
            viewModel.changeDataByLogin(
                PersonEntity = personEntity
            )

            dialog.dismiss()
        }
        dialog.show()
    }

    private fun logout() {
        viewModel.logout()
        viewModel.openAuth()
        subscribe2Logout()
        Toast.makeText(activity, "LOGOUT", Toast.LENGTH_SHORT).show()
    }
}
