package ru.sicampus.bootcamp2025.ui.sign_up;

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
import ru.sicampus.bootcamp2025.databinding.FragmentSignUpBinding;
import ru.sicampus.bootcamp2025.ui.utils.Constants;
import ru.sicampus.bootcamp2025.ui.utils.OnChangeText;
import ru.sicampus.bootcamp2025.ui.utils.PreferenceManager;
import ru.sicampus.bootcamp2025.ui.utils.Utils;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private PreferenceManager preferenceManager;
    private SignUpViewModel viewModel;
    public SignUpFragment() {
        super(R.layout.fragment_sign_up);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSignUpBinding.bind(view);
        preferenceManager = new PreferenceManager(this.getContext());
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.lastName.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeLastName(s.toString());
            }
        });
        binding.firstName.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeFirstName(s.toString());
            }
        });
        binding.username.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeUsername(s.toString());
            }
        });
        binding.password.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changePassword(s.toString());
            }
        });
        binding.passwordConfirm.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeConfirmPassword(s.toString());
            }
        });
        binding.contactsInfo.addTextChangedListener(new OnChangeText() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                viewModel.changeContactInfo(s.toString());
            }
        });
        binding.singUp.setOnClickListener(v -> viewModel.confirm());
        binding.login.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment));
        subscribe(viewModel);
    }

    private void subscribe(SignUpViewModel viewModel) {
        viewModel.errorLiveData.observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        });
        /*viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            binding.confirm.setText(state.getButton());
            binding.title.setText(state.getTitle());
            binding.password.setVisibility(Utils.visibleOrGone(state.isPasswordEnabled()));
        });*/
        viewModel.openListLiveData.observe(getViewLifecycleOwner(), (unused) -> {
            final View view = getView();
            if (view == null) return;
            preferenceManager.putString(Constants.KEY_USER_USERNAME, binding.username.getText().toString());
            view.getRootView().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_mapFragment);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
