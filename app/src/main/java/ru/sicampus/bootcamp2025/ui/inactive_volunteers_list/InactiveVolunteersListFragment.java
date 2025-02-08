package ru.sicampus.bootcamp2025.ui.inactive_volunteers_list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.InactiveVolunteersListFragmentBinding;
import ru.sicampus.bootcamp2025.ui.utils.Utils;
import ru.sicampus.bootcamp2025.ui.volunteer_profile.VolunteerProfileFragment;

public class InactiveVolunteersListFragment extends Fragment {

    private InactiveVolunteersListFragmentBinding binding;

    private InactiveVolunteersListViewModel viewModel;

    public InactiveVolunteersListFragment() {
        super(R.layout.inactive_volunteers_list_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = InactiveVolunteersListFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(InactiveVolunteersListViewModel.class);
        binding.refresh.setOnRefreshListener(() -> viewModel.update());
        final InactiveVolunteersListAdapter adapter = new InactiveVolunteersListAdapter(id -> openProfile(id));
        binding.inactiveVolunteersRecycler.setAdapter(adapter);
        subscribe(viewModel, adapter);
    }

    private void subscribe(final InactiveVolunteersListViewModel viewModel, final InactiveVolunteersListAdapter adapter) {
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            boolean isSuccess = !state.isLoading()
                    && state.getErrorMessage() == null
                    && state.getInactiveUserList() != null;
            binding.refresh.setEnabled(!state.isLoading());
            if (!state.isLoading()) binding.refresh.setRefreshing(false);
            binding.inactiveVolunteersRecycler.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.error.setVisibility(Utils.visibleOrGone(state.getErrorMessage() != null));
            binding.loading.setVisibility(Utils.visibleOrGone(state.isLoading()));
            binding.error.setText(state.getErrorMessage());
            if (isSuccess) {
                adapter.updateData(state.getInactiveUserList());
            }
        });
    }

    private void openProfile(@NonNull String id) {
        final View view = getView();
        if (view == null) return;
        Navigation.findNavController(view).navigate(
                R.id.action_inactiveVolunteersList_to_volunteerProfile,
                VolunteerProfileFragment.getBundle(id));
    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
