package ru.sicampus.bootcamp2025.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.data.VolunteerNetworkDataSource
import ru.sicampus.bootcamp2025.databinding.FragmentListBinding
import ru.sicampus.bootcamp2025.databinding.FragmentMenuBinding
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class FragmentList:Fragment(R.layout.fragment_list) {
    var volunteerNetworkDataSource: VolunteerNetworkDataSource = VolunteerNetworkDataSource()

    private val viewModel by viewModels<ListViewModel> { ListViewModel.Factory }


     lateinit var binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        binding.root

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentListBinding.bind(view)

        binding.refresh.setOnClickListener { viewModel.clickRefresh() }

        val adapter = VolunteerAdapter()
        binding.content.adapter = adapter

        viewModel.state.collectWithLifecycle(this) { state ->
            binding.error.visibility =
                if (state is ListViewModel.State.Error) View.VISIBLE else View.GONE
            binding.loading.visibility =
                if (state is ListViewModel.State.Loading) View.VISIBLE else View.GONE
            binding.content.visibility =
                if (state is ListViewModel.State.Show) View.VISIBLE else View.GONE
            when (state) {
                is ListViewModel.State.Loading -> Unit
                is ListViewModel.State.Show -> {
                    adapter.submitList(state.items)
                }

                is ListViewModel.State.Error -> {
                    binding.errorText.text = state.text
                }
            }

        }

    }








    companion object{
        @JvmStatic
        fun newInstance() = FragmentList()
    }

}
