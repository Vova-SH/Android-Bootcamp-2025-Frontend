package ru.sicampus.bootcamp2025.ui.user_profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.UserProfileFragmentBinding;
import ru.sicampus.bootcamp2025.ui.utils.MyNavigator;

public class UserProfileFragment extends Fragment {

    private UserProfileFragmentBinding binding;
    private UserProfileViewModel viewModel;
    private boolean isEdit = false;

    public UserProfileFragment() {
        super(R.layout.user_profile_fragment);
    }

    private static final String KEY_ID = "id";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = UserProfileFragmentBinding.bind(view);

        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);

        String userId = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (userId != null) {
            viewModel.loadUser(userId);
        }

        binding.edit.setOnClickListener(v -> {
            if (!isEdit) {
                enableEditing(true);
                binding.edit.setImageDrawable(getResources().getDrawable(R.drawable.ok));
                isEdit = true;
            } else {
                String newName = Objects.requireNonNull(binding.name.getEditText().getText().toString());
                String newNickname = Objects.requireNonNull(binding.nickname.getEditText().getText().toString());
                String newEmail = Objects.requireNonNull(binding.email.getEditText().getText().toString());
                String photoUrl = viewModel.stateLiveData.getValue() != null
                        ? viewModel.stateLiveData.getValue().getUser().getPhotoUrl()
                        : null;


                viewModel.updateUserProfile(userId, newName, newNickname, newEmail, photoUrl);
                enableEditing(false);
                binding.edit.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));
                isEdit = false;
            }
        });

        subscribe();
    }

    private void enableEditing(boolean enable) {
        binding.name.setFocusable(enable);
        binding.name.setFocusableInTouchMode(enable);
        binding.name.setClickable(enable);

        binding.nickname.setFocusable(enable);
        binding.nickname.setFocusableInTouchMode(enable);
        binding.nickname.setClickable(enable);

        binding.email.setFocusable(enable);
        binding.email.setFocusableInTouchMode(enable);
        binding.email.setClickable(enable);
    }

    private void subscribe() {
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            if (state.isLoading()) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);

                if (state.getErrorMessage() != null) {
                    Toast.makeText(requireContext(), state.getErrorMessage(), Toast.LENGTH_SHORT).show();
                } else if (state.getUser() != null) {
                    Objects.requireNonNull(binding.name.getEditText().toString());
                    Objects.requireNonNull(binding.nickname.getEditText().toString());
                    Objects.requireNonNull(binding.email.getEditText().toString());
                    if (state.getUser().getPhotoUrl() != null) {
                        Picasso.get().load(state.getUser().getPhotoUrl()).into(binding.image);
                    }
                }
            }
        });

        viewModel.logoutLiveData.observe(getViewLifecycleOwner(), unused -> {
            ((MyNavigator) requireActivity()).onLogout();
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    public static Bundle getBundle(@NonNull String id) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, id);
        return bundle;
    }
}