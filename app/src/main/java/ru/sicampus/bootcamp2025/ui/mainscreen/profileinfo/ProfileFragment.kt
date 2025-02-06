package ru.sicampus.bootcamp2025.ui.mainscreen.profileinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ViewProfileFragmentBinding
import ru.sicampus.bootcamp2025.domain.entities.ProfileEntity
import ru.sicampus.bootcamp2025.ui.utils.collectWithLifecycle
import ru.sicampus.bootcamp2025.ui.utils.visibleOrGone

class ProfileFragment : Fragment(R.layout.view_profile_fragment) {

    private var _binding: ViewProfileFragmentBinding? = null
    private val binding: ViewProfileFragmentBinding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels<ProfileViewModel> { ProfileViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = ViewProfileFragmentBinding.bind(view)

        viewModel.state.collectWithLifecycle(this) { state ->
            binding.error.visibility = visibleOrGone(state is ProfileViewModel.State.Error)
            binding.content.visibility = visibleOrGone(state is ProfileViewModel.State.Show)
            binding.refresh.isRefreshing = state is ProfileViewModel.State.Loading

            when (state) {

                is ProfileViewModel.State.Error -> binding.error.text = state.text
                ProfileViewModel.State.Loading -> Unit
                is ProfileViewModel.State.Show -> {
                    val profile = state.profile
                    Picasso.get().load(profile.photoUrl).into(binding.profilePicture)
                    binding.currentCenter.text = "${profile.centerId}"
                    binding.name.setText(profile.name)
                    binding.lastname.setText(profile.lastname)
                    binding.phone.setText(profile.phoneNumber)
                    binding.email.setText(profile.email)
                }
            }
        }

        binding.refresh.setOnRefreshListener { viewModel.onRefresh() }

        binding.save.setOnClickListener {
            viewModel.onSaveChanges(
                ProfileEntity(
                    name = binding.name.text.toString(),
                    lastname = binding.lastname.text.toString(),
                    phoneNumber = binding.phone.text.toString(),
                    email = binding.phone.text.toString(),
                    centerId = Integer.parseInt(binding.currentCenter.text.toString()),
                    photoUrl = "https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_3x2.jpg"
                )
                // TODO: Add Image sender
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}