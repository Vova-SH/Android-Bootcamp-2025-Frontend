package ru.sicampus.bootcamp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.ProfileBinding
import ru.sicampus.bootcamp.databinding.VolunteerListBinding
import ru.sicampus.bootcamp.ui.Profile.ProfilViewModel
import ru.sicampus.bootcamp.ui.centersList.CentersListFragment
import ru.sicampus.bootcamp.ui.map.MapFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class ProfileFragment : Fragment(R.layout.profile) {
    private var _viewBinding: ProfileBinding? = null
    private val viewBinding: ProfileBinding get() = _viewBinding!!

    private val viewModel by viewModels<ProfilViewModel> { ProfilViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = ProfileBinding.bind(view)


        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is ProfilViewModel.State.Loading -> Unit
                is ProfilViewModel.State.Show -> {
                    viewBinding.username.text = state.items.name.toString() + " " + state.items.secondName + " " + state.items.lastName
                    viewBinding.userphone.text = state.items.phoneNumber
                    viewBinding.useremail.text = state.items.email
                    viewBinding.userbio.text = state.items.info
                }

                is ProfilViewModel.State.Error -> {
                    Log.d("FFF", "${state.text}}")
                }
            }

        }

        viewBinding.mapIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, MapFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.listIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, CentersListFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
