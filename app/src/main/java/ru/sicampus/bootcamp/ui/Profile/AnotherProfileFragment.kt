package ru.sicampus.bootcamp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.ProfileBinding
import ru.sicampus.bootcamp.databinding.VolunteerListBinding
import ru.sicampus.bootcamp.databinding.VolunteerProfileBinding
import ru.sicampus.bootcamp.ui.Profile.AnotherProfileViewModel
import ru.sicampus.bootcamp.ui.centersList.CentersListFragment
import ru.sicampus.bootcamp.ui.map.MapFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class AnotherProfileFragment : Fragment(R.layout.volunteer_profile) {
    private var _viewBinding: VolunteerProfileBinding? = null
    private val viewBinding: VolunteerProfileBinding get() = _viewBinding!!

    private val viewModel by viewModels<AnotherProfileViewModel> { AnotherProfileViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = VolunteerProfileBinding.bind(view)


        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is AnotherProfileViewModel.State.Loading -> Unit
                is AnotherProfileViewModel.State.Show -> {
                    viewBinding.profileText.text = state.items.name.toString() + " " + state.items.secondName + " " + state.items.lastName
                    viewBinding.userphone.text = state.items.phoneNumber
                    viewBinding.useremail.text = state.items.email
                    viewBinding.userbio.text = state.items.info
                }

                is AnotherProfileViewModel.State.Error -> {
                    Log.d("FFF", "${state.text}}")
                }
            }

        }

        viewBinding.mapIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, MapFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.back.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ListFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.listIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, CentersListFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.profileIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ProfileFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
