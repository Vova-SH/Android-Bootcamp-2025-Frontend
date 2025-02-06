package ru.sicampus.bootcamp2025.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentLocationListBinding
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class ListFragment : Fragment(R.layout.fragment_location_list) {
    private var _viewBinding: FragmentLocationListBinding? = null
    private val viewBinding: FragmentLocationListBinding get() = _viewBinding!!

    private val viewModel by viewModels<ListViewModel> { ListViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentLocationListBinding.bind(view)

        val adapter = ListAdapter()
        viewBinding.refresh.setOnClickListener { adapter.refresh() }
        viewBinding.content.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listState.collect { data ->
                    adapter.submitData(data)
                }
            }
        }

        adapter.loadStateFlow.collectWithLifecycle(this) { data ->
            val state = data.refresh
            viewBinding.error.visibility =
                if (state is LoadState.Error) View.VISIBLE else View.GONE
            viewBinding.loading.visibility =
                if (state is LoadState.Loading) View.VISIBLE else View.GONE
            if (state is LoadState.Error) {
                viewBinding.errorText.text = state.error.message.toString()
            }
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
