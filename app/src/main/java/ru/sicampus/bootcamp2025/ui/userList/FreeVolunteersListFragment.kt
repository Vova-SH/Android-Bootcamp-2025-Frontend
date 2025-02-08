package ru.sicampus.bootcamp2025.ui.userList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentFreeVolunteersListBinding
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class FreeVolunteersListFragment : Fragment(R.layout.fragment_free_volunteers_list) {
    private var _viewBinding: FragmentFreeVolunteersListBinding? = null
    private val viewBinding: FragmentFreeVolunteersListBinding get() = _viewBinding!!

    private val viewModel by viewModels<FreeVolunteersListViewModel> { TODO() /*FragmentFreeVolounteersViewModel.Factory*/ }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentFreeVolunteersListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        viewBinding.refresh.setOnClickListener {  viewModel.clickRefresh()}

        val adapter = VolunteerAdapter()
        viewBinding.freeVolunteersList.adapter = adapter


        viewModel.state.collectWithLifecycle(this) { state ->
            viewBinding.error.visibility = if (state is FreeVolunteersListViewModel.State.Error) View.VISIBLE else View.GONE
            viewBinding.loading.visibility = if (state is FreeVolunteersListViewModel.State.Loading) View.VISIBLE else View.GONE
            viewBinding.freeVolunteersList.visibility = if (state is FreeVolunteersListViewModel.State.Show) View.VISIBLE else View.GONE

            when(state) {
                is FreeVolunteersListViewModel.State.Loading -> Unit
                is FreeVolunteersListViewModel.State.Show -> {
                    adapter.submitList(state.items)
                }
                is FreeVolunteersListViewModel.State.Error -> {
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