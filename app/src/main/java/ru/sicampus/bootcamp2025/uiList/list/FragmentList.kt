package ru.sicampus.bootcamp2025.uiList.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
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
        viewBinding.refresh.setOnClickListener { viewModel.clickRefresh() }
        viewModel.state

        val adapter = UserAdapter()
        viewBinding.content.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collectionWithLifecycle(this) { state ->
                    viewBinding.content.visibility =
                        if (state is ListViewModel.State.Show) View.VISIBLE else View.GONE
                    when(state){
                        is ListViewModel.State.Loading -> {
                            //TODO() create loading view
                        }
                        is ListViewModel.State.Show -> {
                            //TODO(): set adapter value
                        }
                        is ListViewModel.State.Error -> {
                            //TODO() error text
                        }
                    }
                }
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
