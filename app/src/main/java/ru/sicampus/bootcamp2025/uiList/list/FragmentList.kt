package ru.sicampus.bootcamp2025.uiList.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.VolunteerListBinding
import ru.sicampus.bootcamp2025.util.collectionWithLifecycle

class FragmentList : Fragment(R.layout.volunteer_list)
{
    private var _viewBinding: VolunteerListBinding? = null
    private val viewBinding: VolunteerListBinding get() = _viewBinding!!

    private val viewModel by viewModels<ListViewModel> { ListViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = VolunteerListBinding.bind(view)

        val adapter = UserAdapter()
        viewBinding.refresh.setOnClickListener { adapter.refresh() }
        viewBinding.content.adapter = adapter

        viewModel.listState.collectionWithLifecycle(this) { data ->
            adapter.submitData(data)
        }

        adapter.loadStateFlow.collectionWithLifecycle(this) { data ->
            val state = data.refresh
            viewBinding.error.visibility =
                if (state is LoadState.Loading) View.VISIBLE else View.GONE
            if (state is LoadState.Error) {
                viewBinding.errorText.text = state.error.message.toString()
            }
        }


        val viewModel: ListViewModel
        val state: ListViewModel.State = ListViewModel.State.Loading
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

}
