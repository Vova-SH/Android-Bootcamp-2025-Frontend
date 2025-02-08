package ru.sicampus.bootcamp2025.ui.one

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentOneCenterBinding
import ru.sicampus.bootcamp2025.domain.one.OneCenter

class OneCenterFragment : Fragment(R.layout.fragment_one_center) {

    private var _viewBinding: FragmentOneCenterBinding? = null
    private val viewBinding: FragmentOneCenterBinding get() = _viewBinding!!
    private lateinit var adapter: UserAdapter

    private val viewModel: OneCenterViewModel by viewModels()

    companion object {
        private const val ARG_ID = "id"
        private const val ARG_NAME = "name"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_LATITUDE = "latitude"
        private const val ARG_LONGITUDE = "longitude"

        fun newInstance(oneCenter: OneCenter): OneCenterFragment {
            val fragment = OneCenterFragment()
            val args = Bundle().apply {
                putInt(ARG_ID, oneCenter.id)
                putString(ARG_NAME, oneCenter.name)
                putString(ARG_DESCRIPTION, oneCenter.description)
                putDouble(ARG_LATITUDE, oneCenter.coordinates.latitude)
                putDouble(ARG_LONGITUDE, oneCenter.coordinates.longitude)
            }
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = FragmentOneCenterBinding.bind(view)

        setupViews()
        setupRecyclerView()
        observeViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {
        viewBinding.title.text = viewModel.oneCenter.name
        viewBinding.description.text = viewModel.oneCenter.description
        viewBinding.coordinates.text =
            "${viewModel.oneCenter.coordinates.latitude} ${viewModel.oneCenter.coordinates.longitude}"
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter(emptyList()) {
            viewModel.registerUser(id)
        }

        viewBinding.usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@OneCenterFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is OneCenterViewModel.State.Loading -> showLoading()
                is OneCenterViewModel.State.Loaded -> showContent(state)
                is OneCenterViewModel.State.Error -> showError(state.message)
            }
        }
    }

    private fun showLoading() {
        viewBinding.progressBar.visibility = View.VISIBLE
        viewBinding.usersRecyclerView.visibility = View.GONE
    }

    private fun showContent(state: OneCenterViewModel.State.Loaded) {
        viewBinding.progressBar.visibility = View.GONE
        viewBinding.usersRecyclerView.visibility = View.VISIBLE
        adapter.submitList(state.volunteers)
    }

    private fun showError(message: String) {
        viewBinding.progressBar.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}