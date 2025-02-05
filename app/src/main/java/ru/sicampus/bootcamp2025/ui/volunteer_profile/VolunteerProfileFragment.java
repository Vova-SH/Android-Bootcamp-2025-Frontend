package ru.sicampus.bootcamp2025.ui.volunteer_profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.VolunteerProfileFragmentBinding;
import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.ui.utils.Utils;

public class VolunteerProfileFragment extends Fragment {

    private static final String KEY_ID = "id";

    private VolunteerProfileFragmentBinding binding;

    private VolunteerProfileViewModel viewModel;

    public VolunteerProfileFragment() {
        super(R.layout.volunteer_profile_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = VolunteerProfileFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(VolunteerProfileViewModel.class);

        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            if (state.isLoading()) {
                showLoading();
            } else if (state.getErrorMessage() != null) {
                showError(state.getErrorMessage());
            } else {
                final FullUserEntity entity = state.getUser();
                if (entity == null) {
                    showError("User data is not available");
                } else {
                    showUserData(entity);
                }
            }
        });
        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");
        viewModel.load(id);
        binding.arrowBack.setOnClickListener(v -> {
            viewModel.goBack();
        });
    }

    private void showLoading() {
        binding.loading.setVisibility(View.VISIBLE);
        binding.error.setVisibility(View.GONE);
        binding.container.setVisibility(View.GONE);
        binding.image.setVisibility(View.GONE);
        binding.name.setVisibility(View.GONE);
        binding.nickname.setVisibility(View.GONE);
        binding.email.setVisibility(View.GONE);
        binding.name.setVisibility(View.GONE);
        binding.volunteerTitle.setVisibility(View.GONE);
        binding.phone.setVisibility(View.GONE);
        binding.arrowBack.setVisibility(View.VISIBLE);
    }

    private void showError(String errorMessage) {
        binding.loading.setVisibility(View.GONE);
        binding.error.setVisibility(View.VISIBLE);
        binding.container.setVisibility(View.GONE);
        binding.image.setVisibility(View.GONE);
        binding.name.setVisibility(View.GONE);
        binding.nickname.setVisibility(View.GONE);
        binding.email.setVisibility(View.GONE);
        binding.phone.setVisibility(View.GONE);
        binding.name.setVisibility(View.GONE);
        binding.volunteerTitle.setVisibility(View.GONE);
        binding.error.setText(errorMessage);
        binding.arrowBack.setVisibility(View.VISIBLE);
    }

    private void showUserData(FullUserEntity entity) {
        binding.loading.setVisibility(View.GONE);
        binding.error.setVisibility(View.GONE);
        binding.container.setVisibility(View.VISIBLE);


        binding.image.setVisibility(Utils.visibleOrGone(entity.getPhotoUrl() != null));
        binding.phone.setVisibility(Utils.visibleOrGone(entity.getPhone() != null));


        binding.name.setText(entity.getName());
        binding.nickname.setText(entity.getNickname());
        binding.email.setText(entity.getEmail());
        binding.phone.setText(entity.getPhone());

        if (entity.getPhotoUrl() != null) {
            Picasso.get().load(entity.getPhotoUrl()).into(binding.image);
        }
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