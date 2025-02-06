package ru.sicampus.bootcamp2025.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentListBinding
import ru.sicampus.bootcamp2025.util.collectWithLifecycle

class ListFragment: Fragment(R.layout.fragment_list){
    private var _viewBinding: FragmentListBinding? = null
    private val viewBinding: FragmentListBinding get() = _viewBinding!!

    private val viewModel by viewModels<ListViewModel> { ListViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = FragmentListBinding.bind(view)
        val adapter = UserAdapter()

        viewBinding.content.adapter = adapter

        viewBinding.refresh.setOnClickListener { adapter.refresh() }

        viewModel.listState.collectWithLifecycle(this) { data ->
            adapter.submitData(data)
        }

        adapter.loadStateFlow.collectWithLifecycle(this) { data ->
            val state = data.refresh
            viewBinding.error.visibility =
                if (state is LoadState.Error) View.VISIBLE else View.GONE
            viewBinding.loading.visibility =
                if (state is LoadState.Error) View.VISIBLE else View.GONE
            if (state is LoadState.Error) {
                viewBinding.errorText.text = state.error.message.toString()
            }

        }

//        viewModel.state.collectWithLifecycle(this) { state ->
//            viewBinding.error.visibility = if(state is ListViewModel.State.Error) View.VISIBLE else View.GONE
//            viewBinding.loading.visibility = if(state is ListViewModel.State.Error) View.VISIBLE else View.GONE
//            viewBinding.content.visibility = if(state is ListViewModel.State.Error) View.VISIBLE else View.GONE
//            when (state) {
//                is ListViewModel.State.Loading -> Unit
//                is ListViewModel.State.Show -> {
//                    adapter.submitList(state.items)
//                }
//                is ListViewModel.State.Error -> {
//                    viewBinding.text.text = state.text
//                }
//            }
//        }
    }



    override fun onDestroy() {
        _viewBinding = null
        super.onDestroy()
    }
}