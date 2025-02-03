package ru.sicampus.bootcamp2025.ui.volunteers_centers_list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.VolunteerCentresListFragmentBinding;

public class VolunteerCentersListFragment extends Fragment {

    private VolunteerCentresListFragmentBinding binding;

    private VolunteersCentersListViewModel viewModel;

    VolunteerCentersListFragment() {
        super(R.layout.volunteer_centres_list_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = VolunteerCentresListFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(VolunteersCentersListViewModel.class);
        binding.refresh.setOnRefreshListener(() -> viewModel.update());
        VolunteersCentersListAdapter adapter = new VolunteersCentersListAdapter();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
