package ru.sicampus.bootcamp2025.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentRegisterFirstBinding
import ru.sicampus.bootcamp2025.util.collectWithLifecycle

class RegisterFirstFragment : Fragment(R.layout.fragment_register_first) {
    private var _binding : FragmentRegisterFirstBinding? = null
    private val binding : FragmentRegisterFirstBinding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegisterFirstBinding.bind(view)

        binding.nextBtn.setOnClickListener{
            viewModel.clickNext(
                binding.fullname.text.toString(),
                binding.email.text.toString(),
                binding.password.text.toString()
            )
            Navigation.findNavController(view).navigate(R.id.action_registerFirstFragment_to_registerSecondFragment)
        }


        viewModel.state.collectWithLifecycle(this){ state ->
            binding.nextBtn.isEnabled = state is RegisterViewModel.State.Show
            binding.fullname.isEnabled = state is RegisterViewModel.State.Show
            binding.email.isEnabled = state is RegisterViewModel.State.Show
            binding.password.isEnabled = state is RegisterViewModel.State.Show


            if(state is RegisterViewModel.State.Show){
                binding.errorText.visibility = if(state.errorText != null) View.VISIBLE else View.GONE
            }

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}