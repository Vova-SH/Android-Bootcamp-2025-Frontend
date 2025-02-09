package ru.sicampus.bootcamp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.VolunteerListBinding
import ru.sicampus.bootcamp.ui.list.AnotherProfileFragment
import ru.sicampus.bootcamp.ui.centersList.CentersListFragment
import ru.sicampus.bootcamp.ui.map.MapFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class ListFragment : Fragment(R.layout.volunteer_list), OpenProfile {
    private var _viewBinding: VolunteerListBinding? = null
    private val viewBinding: VolunteerListBinding get() = _viewBinding!!

    private val viewModel by viewModels<ListViewModel> { ListViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = VolunteerListBinding.bind(view)
        viewBinding.refresh.setOnClickListener { viewModel.clickRefresh() }

        val adapter = UserAdapter(this)
        viewBinding.content.adapter = adapter

        viewModel.state.collectWithLifecycle(this) { state ->
            viewBinding.error.visibility =
                if (state is ListViewModel.State.Error) View.VISIBLE else View.GONE
            when (state) {
                is ListViewModel.State.Loading -> Unit
                is ListViewModel.State.Show -> {
                    adapter.submitList(state.items)
                }

                is ListViewModel.State.Error -> {
                    viewBinding.errorText.text = state.text
                }
            }

        }
        viewBinding.profileIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ProfileFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.mapIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, MapFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.toCenterList.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, CentersListFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
    override fun goToProfile(username: String) {
       parentFragmentManager.beginTransaction()
           .replace(R.id.main, AnotherProfileFragment()).addToBackStack(null)
           .commitAllowingStateLoss()
    }
}
