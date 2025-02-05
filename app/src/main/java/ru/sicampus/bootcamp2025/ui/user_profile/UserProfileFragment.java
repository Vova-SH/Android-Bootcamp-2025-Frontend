package ru.sicampus.bootcamp2025.ui.user_profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.UserProfileFragmentBinding;

public class UserProfileFragment extends Fragment {

    private UserProfileFragmentBinding binding;

    public UserProfileFragment() {
        super(R.layout.user_profile_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = UserProfileFragmentBinding.bind(view);

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
