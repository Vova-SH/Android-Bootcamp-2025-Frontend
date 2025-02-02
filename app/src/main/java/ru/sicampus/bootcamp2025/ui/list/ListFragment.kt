package ru.sicampus.bootcamp2025.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentLocationListBinding
import ru.sicampus.bootcamp2025.utils.collectFlowLifecycle
import ru.sicampus.bootcamp2025.utils.setVisibleOrGone

class ListFragment : Fragment(R.layout.fragment_location_list) {
    private var _viewBinding: FragmentLocationListBinding? = null
    private val viewBinding: FragmentLocationListBinding get() = _viewBinding!!

    private val viewModel: ListViewModel by viewModels { ListViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentLocationListBinding.bind(view)

        viewBinding.refresh.setOnClickListener { viewModel.clickRefresh() }

        val adapter = ListAdapter(requireContext())
        viewBinding.content.adapter = adapter

        viewModel._state.collectFlowLifecycle(this) { state ->
            viewBinding.error.setVisibleOrGone(state is ListViewModel.State.Error)
            viewBinding.loading.setVisibleOrGone(state is ListViewModel.State.Loading)
            viewBinding.content.setVisibleOrGone(state is ListViewModel.State.Show)

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
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}