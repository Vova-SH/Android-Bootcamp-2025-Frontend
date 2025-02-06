package ru.sicampus.bootcamp2025.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentRegisterFirstBinding
import ru.sicampus.bootcamp2025.ui.vlist.FreeVolunteersListViewModel
import ru.sicampus.bootcamp2025.util.collectWithLifecycle
import kotlin.getValue

class RegisterFirstFragment : Fragment(R.layout.fragment_register_first) {
    private var _binding : FragmentRegisterFirstBinding? = null
    private val binding : FragmentRegisterFirstBinding get() = _binding!!
    private val viewModel by activityViewModels<RegisterViewModel>{ RegisterViewModel.Factory}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegisterFirstBinding.bind(view)

        binding.registerBtn.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.registerBtn.isEnabled = binding.fullname.text.isNotBlank() && binding.email.text.isNotBlank() && binding.password.text.isNotBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }

        binding.fullname.addTextChangedListener(textWatcher)
        binding.email.addTextChangedListener(textWatcher)
        binding.password.addTextChangedListener(textWatcher)

        binding.registerBtn.setOnClickListener{
            if(binding.registerBtn.isEnabled){
                viewModel.clickNext(
                    binding.fullname.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
                Navigation.findNavController(view).navigate(R.id.action_registerFirstFragment_to_registerSecondFragment)
            }

        }


        viewModel.state.collectWithLifecycle(this){ state ->
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