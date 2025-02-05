package ru.sicampus.bootcamp2025.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding
import ru.sicampus.bootcamp2025.domain.UserEntity
import ru.sicampus.bootcamp2025.ui.vlist.FreeVolunteersListViewModel
import ru.sicampus.bootcamp2025.util.collectWithLifecycle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding : FragmentProfileBinding? = null
    private val binding : FragmentProfileBinding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>{ProfileViewModel.Factory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentProfileBinding.bind(view)
        binding.refreshBtn.setOnClickListener{ viewModel.clickRefresh() }
        viewModel.state.collectWithLifecycle(this){ state ->
            binding.error.visibility = if(state is ProfileViewModel.State.Error) View.VISIBLE else View.GONE
            binding.loading.visibility = if(state is ProfileViewModel.State.Loading) View.VISIBLE else View.GONE
            binding.content.visibility = if(state is ProfileViewModel.State.Show) View.VISIBLE else View.GONE

            when(state){
                is ProfileViewModel.State.Error -> {
                    binding.errorText.text = state.text
                }
                is ProfileViewModel.State.Loading -> Unit
                is ProfileViewModel.State.Show -> {
                    showProfile(state.user)
                }
            }

        }
    }
    private fun showProfile(user: UserDTO) {
        binding.loading.visibility = View.GONE
        with(binding) {
            fullNameText.text = "${user.name}"
            organizationName.text = "${user.organizationName}"
            roleText.text = "${user.role}"
            birthDateText.text = dateConverter(user.birthDate)
            phoneText.text = "${user.phoneNumber}"
            emailText.text = "${user.email}"
            telegramText.text = "${user.telegramUsername}"
            aboutText.text = "${user.about}"
            Picasso.get().load(user.photoUrl).into(photo)

        }
    }
  private fun dateConverter(date: Date?) : String {
      if (date != null) {
          val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
          println(dateFormat.format(date).toString())
          return dateFormat.format(date).toString()

      }
      return ""
  }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}