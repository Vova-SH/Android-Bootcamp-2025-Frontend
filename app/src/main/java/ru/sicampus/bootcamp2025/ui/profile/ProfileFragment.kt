package ru.sicampus.bootcamp2025.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _viewBinding: FragmentProfileBinding? = null
    private val viewBinding: FragmentProfileBinding get() = _viewBinding!!

    private val viewModel by viewModels<ProfileViewModel> { ProfileViewModel.Factory }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentProfileBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        

        viewBinding.refresh.setOnClickListener{ viewModel.clickRefresh() }

        viewBinding.logout.setOnClickListener{
            TODO("нужно короче logout сделать")
        }



        viewBinding.editButton.setOnClickListener {
            viewBinding.edit.visibility = View.GONE
            viewBinding.save.visibility = View.VISIBLE
            viewBinding.email.isFocusable = true
            viewBinding.email.isClickable = true
            viewBinding.birthdayData.isClickable = true
            viewBinding.birthdayData.isFocusable = true
            viewBinding.aboutMe.isClickable = true
            viewBinding.aboutMe.isFocusable = true
            viewModel.updateStateGet()
        }

        viewBinding.saveButton.setOnClickListener {
            viewBinding.edit.visibility = View.VISIBLE
            viewBinding.save.visibility = View.GONE
            viewBinding.email.isClickable = false
            viewBinding.email.isFocusable = false
            viewBinding.birthdayData.isClickable = false
            viewBinding.birthdayData.isFocusable = false
            viewBinding.aboutMe.isClickable = false
            viewBinding.aboutMe.isFocusable = false
            //update_state_post()
        }


        viewModel.state.collectWithLifecycle(this) { state ->

            viewBinding.error.visibility = if (state is ProfileViewModel.State.Error) View.VISIBLE else View.GONE
            viewBinding.loading.visibility = if (state is ProfileViewModel.State.Loading) View.VISIBLE else View.GONE
            viewBinding.profile.visibility = if (state is ProfileViewModel.State.Show) View.VISIBLE else View.GONE

            when(state) {
                is ProfileViewModel.State.Loading -> Unit
                is ProfileViewModel.State.Show -> {
                    Log.d("itemsInFragment", state.items.toString())
                    viewBinding.name.text = state.items.name
                    viewBinding.centerTitle.text = state.items.center
                    viewBinding.centerDescription.text = state.items.centerDescription
                    viewBinding.email.setText(state.items.email)
                    viewBinding.birthdayData.setText(state.items.birthDate)
                    viewBinding.aboutMe.setText(state.items.description)
                    Picasso.get().load(state.items.avatarUrl).resize(100, 100).centerCrop().into(viewBinding.avatar)
                }
                is ProfileViewModel.State.Error -> {
                    viewBinding.errorText.text = state.text
                }
            }

        }


    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

}