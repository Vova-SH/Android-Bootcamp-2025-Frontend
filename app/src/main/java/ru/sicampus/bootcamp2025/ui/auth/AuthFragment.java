package ru.sicampus.bootcamp2025.ui.auth;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.AuthenticationFragmentBinding;
import ru.sicampus.bootcamp2025.ui.utils.OnChangeText;

public class AuthFragment extends Fragment {

    private AuthenticationFragmentBinding binding;

    private AuthViewModel viewModel;

    public AuthFragment() {
        super(R.layout.registration_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = AuthenticationFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
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
        subscribe(viewModel);
    }

    private void subscribe(AuthViewModel viewModel) {

    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
