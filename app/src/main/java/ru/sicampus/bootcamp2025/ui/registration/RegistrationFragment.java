package ru.sicampus.bootcamp2025.ui.registration;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.RegistrationFragmentBinding;
import ru.sicampus.bootcamp2025.ui.utils.OnChangeText;

public class RegistrationFragment extends Fragment {

    private RegistrationFragmentBinding binding;

    private RegistrationViewModel viewModel;

    public RegistrationFragment(){
        super(R.layout.registration_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = RegistrationFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        binding.etEmail.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                viewModel.changeLogin(editable.toString());
            }
        });
        binding.etPassword.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                viewModel.changePassword(editable.toString());
            }
        });
        binding.etName.addTextChangedListener(new OnChangeText(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                viewModel.changeName(editable.toString());
            }
        });
        binding.etNickname.addTextChangedListener(new OnChangeText(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                viewModel.changeNickname(editable.toString());
            }
        });
        subscribe(viewModel);
    }

    private void subscribe(RegistrationViewModel viewModel) {

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
