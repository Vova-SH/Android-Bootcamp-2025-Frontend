package ru.sicampus.bootcamp2025.ui.auth;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
        binding.etNickname.addTextChangedListener(new OnChangeText() {
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
        binding.btnLogin.setOnClickListener(v -> viewModel.authenticateUser());
        binding.tvHint.setOnClickListener(v -> openRegistration());
        subscribe(viewModel);
    }

    private void subscribe(AuthViewModel viewModel) {
        viewModel.errorLiveData.observe(getViewLifecycleOwner(), error ->
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show()
        );
        viewModel.openSignLiveData.observe(getViewLifecycleOwner(), unused ->
                openRegistration()
        );
        viewModel.openUserProfileLiveData.observe(getViewLifecycleOwner(),
                unused -> openUserProfileList());
    }

    private void openRegistration() {
        final View view = getView();
        if (view != null) {
            Navigation.findNavController(view).navigate(
                    R.id.action_registrationFragment_to_authFragment);

        }
    }

    private void openUserProfileList() {
        final View view = getView();
        if (view != null) {
            Navigation.findNavController(view).navigate(
                    R.id.action_authFragment_to_userProfileFragment);
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
