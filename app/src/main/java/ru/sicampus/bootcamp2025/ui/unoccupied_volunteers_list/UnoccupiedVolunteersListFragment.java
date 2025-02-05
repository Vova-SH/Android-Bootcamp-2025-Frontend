package ru.sicampus.bootcamp2025.ui.unoccupied_volunteers_list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.UnoccupiedVolunteersListFragmentBinding;

public class UnoccupiedVolunteersListFragment extends Fragment {
    private UnoccupiedVolunteersListFragmentBinding binding;
    private UnoccupiedVolunteersListViewModel viewModel;

    public UnoccupiedVolunteersListFragment(){
        super(R.layout.unoccupied_volunteers_list_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = UnoccupiedVolunteersListFragmentBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(UnoccupiedVolunteersListViewModel.class);
        binding.refresh.setOnRefreshListener(() -> viewModel.update());
        UnoccupiedVolunteersListAdapter adapter = new UnoccupiedVolunteersListAdapter();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
