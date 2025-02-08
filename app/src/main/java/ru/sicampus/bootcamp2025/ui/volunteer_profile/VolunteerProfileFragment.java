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
        subscribe(viewModel);
        String id = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");
        viewModel.load(id);
    }

    private void subscribe(final VolunteerProfileViewModel viewModel) {
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            final FullUserEntity entity = state.getUser();
            boolean isSuccess = !state.isLoading()
                    && state.getErrorMessage() == null
                    && state.getUser() != null;
            binding.error.setVisibility(Utils.visibleOrGone(state.getErrorMessage() != null));
            binding.error.setText(state.getErrorMessage());
            binding.loading.setVisibility(Utils.visibleOrGone(state.isLoading()));

            binding.container.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.image.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.name.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.nickname.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.email.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.name.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.volunteerTitle.setVisibility(Utils.visibleOrGone(isSuccess));

            if (isSuccess) {
                binding.name.setText(entity.getName());
                binding.nickname.setText(entity.getNickname());
                binding.email.setText(entity.getEmail());
                if (entity.getPhotoUrl() != null) {
                    Picasso.get().load(entity.getPhotoUrl()).into(binding.image);
                }
                binding.image.setVisibility(Utils.visibleOrGone(entity.getPhotoUrl() != null));
            }

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