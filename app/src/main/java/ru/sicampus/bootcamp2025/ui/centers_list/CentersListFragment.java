package ru.sicampus.bootcamp2025.ui.centers_list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.VolunteerCentresListFragmentBinding;
import ru.sicampus.bootcamp2025.ui.center.CenterFragment;
import ru.sicampus.bootcamp2025.ui.utils.Utils;

public class CentersListFragment extends Fragment {

    private VolunteerCentresListFragmentBinding binding;

    private CentersListViewModel viewModel;

    public CentersListFragment() {
        super(R.layout.volunteer_centres_list_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = VolunteerCentresListFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(CentersListViewModel.class);
        binding.refresh.setOnRefreshListener(() -> viewModel.update());
        final CentersListAdapter adapter = new CentersListAdapter(id -> openCenter(id));
        binding.volunteerCentresRecycler.setAdapter(adapter);
        binding.showVolunteersList.setOnClickListener(v -> openVolunteersList());
        subscribe(viewModel, adapter);
    }

    private void openVolunteersList() {
        final View view = getView();
        if (view == null) return;
        Navigation.findNavController(view).navigate(
                R.id.action_centersList_to_inactiveVolunteersList);
    }

    private void openCenter(@NonNull String id) {
        final View view = getView();
        if (view == null) return;
        Navigation.findNavController(view).navigate(
                R.id.action_centersList_to_centerFragment,
                CenterFragment.getBundle(id));
    }

    private void subscribe(final CentersListViewModel viewModel, final CentersListAdapter adapter) {
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            boolean isSuccess =  !state.isLoading()
                    && state.getErrorMessage() == null
                    && state.getCentersList() != null;
            binding.refresh.setEnabled(!state.isLoading());
            if (!state.isLoading()) binding.refresh.setRefreshing(false);
            binding.volunteerCentresRecycler.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.error.setVisibility(Utils.visibleOrGone(state.getErrorMessage() != null));
            binding.loading.setVisibility(Utils.visibleOrGone(state.isLoading()));

            binding.error.setText(state.getErrorMessage());
            if (isSuccess) {
                adapter.updateData(state.getCentersList());
            }
        });

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
