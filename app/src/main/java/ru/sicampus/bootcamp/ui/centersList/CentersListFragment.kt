package ru.sicampus.bootcamp.ui.centersList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.databinding.CenterListBinding
import ru.sicampus.bootcamp.ui.list.CenterListViewModel
import ru.sicampus.bootcamp.ui.list.CentersAdapter
import ru.sicampus.bootcamp.ui.list.ListViewModel
import ru.sicampus.bootcamp.ui.list.ProfileFragment
import ru.sicampus.bootcamp.ui.map.MapFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class CentersListFragment : Fragment(R.layout.center_list) {
    private var _viewBinding: CenterListBinding? = null
    private val viewBinding: CenterListBinding get() = _viewBinding!!

    private val viewModel by viewModels<CenterListViewModel> { CenterListViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = CenterListBinding.bind(view)
        viewBinding.refresh.setOnClickListener { viewModel.clickRefresh() }

        val adapter = CentersAdapter()
        viewBinding.content.adapter = adapter

        viewModel.state.collectWithLifecycle(this) { state ->
            viewBinding.errorText.visibility =
                if (state is CenterListViewModel.State.Error) View.VISIBLE else View.GONE
            when (state) {
                is CenterListViewModel.State.Loading -> Unit
                is CenterListViewModel.State.Show -> {
                    adapter.submitList(state.items)
                }

                is CenterListViewModel.State.Error -> {
                    viewBinding.errorText.text = state.text
                }

                else -> {
                }
            }

        }
        viewBinding.profileIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ProfileFragment())
                .commitAllowingStateLoss()}
        viewBinding.mapIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, MapFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.toVolunteerList.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ru.sicampus.bootcamp.ui.list.ListFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
