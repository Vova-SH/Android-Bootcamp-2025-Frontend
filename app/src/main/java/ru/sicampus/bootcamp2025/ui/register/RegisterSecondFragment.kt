package ru.sicampus.bootcamp2025.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import ru.sicampus.bootcamp2025.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import ru.sicampus.bootcamp2025.databinding.FragmentRegisterFirstBinding
import ru.sicampus.bootcamp2025.databinding.FragmentRegisterSecondBinding
import ru.sicampus.bootcamp2025.ui.vlist.FreeVolunteersListViewModel
import ru.sicampus.bootcamp2025.util.collectWithLifecycle
import kotlin.getValue

class RegisterSecondFragment : Fragment(R.layout.fragment_register_second) {
    private var _binding : FragmentRegisterSecondBinding? = null
    private val binding : FragmentRegisterSecondBinding get() = _binding!!
    private val viewModel by activityViewModels<RegisterViewModel>{ RegisterViewModel.Factory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegisterSecondBinding.bind(view)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.registerBtn.isEnabled = binding.phone.text.isNotBlank() && binding.tg.text.isNotBlank() && binding.about.text.isNotBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }

        binding.phone.addTextChangedListener(textWatcher)
        binding.tg.addTextChangedListener(textWatcher)
        binding.about.addTextChangedListener(textWatcher)

        binding.registerBtn.setOnClickListener{
            if(binding.registerBtn.isEnabled){
                viewModel.clickRegister(
                    binding.phone.text.toString(),
                    binding.tg.text.toString(),
                    binding.about.text.toString()
                )
                viewModel.registerUser()
                //Navigation.findNavController(view).navigate(R.id.action_registerFirstFragment_to_registerSecondFragment)
            }

        }
        viewModel.state.collectWithLifecycle(this){ state ->
            binding.phone.isEnabled = state is RegisterViewModel.State.Show
            binding.tg.isEnabled = state is RegisterViewModel.State.Show
            binding.about.isEnabled = state is RegisterViewModel.State.Show


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