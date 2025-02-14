package ru.sicampus.bootcamp2025.ui.sign_in;

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
import ru.sicampus.bootcamp2025.databinding.FragmentSignInBinding;
import ru.sicampus.bootcamp2025.ui.utils.Constants;
import ru.sicampus.bootcamp2025.ui.utils.OnChangeText;
import ru.sicampus.bootcamp2025.ui.utils.PreferenceManager;
import ru.sicampus.bootcamp2025.ui.utils.Utils;

public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;
    private PreferenceManager preferenceManager;
    private SignInViewModel viewModel;
    public SignInFragment() {
        super(R.layout.fragment_sign_in);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSignInBinding.bind(view);
        preferenceManager = new PreferenceManager(this.getContext());
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
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
        binding.singIn.setOnClickListener(v -> viewModel.confirm());
        binding.register.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment));
        subscribe(viewModel);
    }

    private void subscribe(SignInViewModel viewModel) {
        viewModel.errorLiveData.observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        });
        /*viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            binding.confirm.setText(state.getButton());
            binding.title.setText(state.getTitle());
            binding.password.setVisibility(Utils.visibleOrGone(state.isPasswordEnabled()));
        });*/
        viewModel.openListLiveData.observe(getViewLifecycleOwner(), username -> {
            final View view = getView();
            if (view == null) return;
            preferenceManager.putString(Constants.KEY_USER_USERNAME, username);
            view.getRootView().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_mapFragment);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
