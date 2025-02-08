package ru.sicampus.bootcamp2025.ui.center;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.VolunteerCenterFragmentBinding;
import ru.sicampus.bootcamp2025.ui.centers_list.CentersListAdapter;
import ru.sicampus.bootcamp2025.ui.volunteer_profile.VolunteerProfileFragment;

public class CenterFragment extends Fragment {

    private static final String KEY_ID = "id";

    private VolunteerCenterFragmentBinding binding;

    private CenterViewModel viewModel;

    public CenterFragment() {
        super(R.layout.volunteer_center_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = VolunteerCenterFragmentBinding.bind(view);
        final String centerId = getArguments() != null ? getArguments().getString(KEY_ID) : null;
        if (centerId == null) {
            Toast.makeText(requireContext(), "Id cannot be null", Toast.LENGTH_SHORT).show();
            return;
        }
        binding.refresh.setOnRefreshListener(() -> viewModel.loadVolunteers(centerId));
        viewModel = new ViewModelProvider(this).get(CenterViewModel.class);
        final CenterAdapter adapter = new CenterAdapter(id -> openProfile(id));
        binding.activeVolunteers.setAdapter(adapter);
        subscribe(adapter);
    }

    private void openProfile(String id) {
        final View view = getView();
        if (view == null) return;
        Navigation.findNavController(view).navigate(
                R.id.action_centerFragment_to_volunteerProfile,
                VolunteerProfileFragment.getBundle(id));
    }

    private void subscribe(final CenterAdapter adapter) {
        viewModel.liveDataCenterState.observe(getViewLifecycleOwner(), state -> {
            if (state.isLoading()) {
                binding.refresh.setVisibility(View.VISIBLE);
            } else {
                binding.refresh.setVisibility(View.GONE);

                if (state.getErrorMessage() != null) {
                    Toast.makeText(requireContext(), "Ошибка: " + state.getErrorMessage(), Toast.LENGTH_SHORT).show();
                } else if (state.getCenter() != null) {
                    binding.centerName.setText(state.getCenter().getCentreName());
                }
            }
        });

        viewModel.liveDataUserListState.observe(getViewLifecycleOwner(), state -> {
            if (state.isLoading()) {
                binding.activeVolunteers.setVisibility(View.GONE);
                binding.refresh.setVisibility(View.VISIBLE);
            } else {
                binding.activeVolunteers.setVisibility(View.VISIBLE);
                binding.refresh.setVisibility(View.GONE);

                if (state.getErrorMessage() != null) {
                    Toast.makeText(requireContext(),
                            "Ошибка загрузки волонтёров: " + state.getErrorMessage(), Toast.LENGTH_SHORT).show();
                } else if (state.getUserList() != null) {
                    adapter.updateData(state.getUserList());
                }
            }
        });
    }

    private void openVolunteerProfile(String id) {
        View view = getView();
        if (view == null) return;
        Navigation.findNavController(view).navigate(
                R.id.action_centerFragment_to_volunteerProfile,
                getBundle(id)
        );
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