package ru.sicampus.bootcamp2025.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.FragmentListBinding;
import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.ui.utils.Constants;
import ru.sicampus.bootcamp2025.ui.utils.PreferenceManager;
import ru.sicampus.bootcamp2025.ui.utils.Utils;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListViewModel viewModel;
    private List<ItemVolunteerCenterEntity> volunteerCenters;
    private FullVolunteerCenterEntity volunteerCenter;
    private PreferenceManager preferenceManager;
    private boolean loadingCenters;
    private boolean loadingCenter;

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentListBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);
        //binding.refresh.setOnRefreshListener(() -> viewModel.update());
        final ListAdapter listAdapter = new ListAdapter(id -> openProfile(id));
        binding.recycler.setAdapter(listAdapter);

        preferenceManager = new PreferenceManager(this.getContext());
        String volunteerCenterId = preferenceManager.getString(Constants.KEY_VOLUNTEER_CENTER_ID);

        if (volunteerCenterId != null) {
            //Toast.makeText(this.getContext(), volunteerCenterId, Toast.LENGTH_SHORT).show();


            binding.spinner.setSelection(Integer.parseInt(volunteerCenterId) - 1, true);
        }




        subscribe(viewModel, listAdapter, view);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(view.getContext(), position + "", Toast.LENGTH_SHORT).show();
                if (loadingCenter == false && loadingCenters == false) {
                    viewModel.changeSelectedVCId(position + 1);
                    //binding.countVolunteers.setText("Количество волонтёров: " + volunteerCenter.getUsers().size());
                    viewModel.getVCById();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        viewModel.load();
    }

    private void subscribe(final ListViewModel viewModel, final ListAdapter listAdapter, View view) {
        viewModel.stateVolunteerCenterLiveData.observe(getViewLifecycleOwner(), state -> {
            loadingCenter = state.isLoading();
            //Toast.makeText(this.getContext(), "Zashol", Toast.LENGTH_SHORT).show();
            binding.loading.setVisibility(Utils.visibleOrGone(loadingCenter));
            if (state.isLoading() == false) {
                if (state.getVolunteerCenter() != null) {
                    volunteerCenter = state.getVolunteerCenter();

                    binding.address.setText(volunteerCenter.getAddress());
                    binding.countVolunteers.setText("Количество волонтёров: " + volunteerCenter.getUsers().size());
                    List<ItemUserEntity> itemUserEntities = new ArrayList<>();
                    for (FullUserEntity el : volunteerCenter.getUsers()) {
                        ItemUserEntity user = new ItemUserEntity(el.getId(), el.getLastName(), el.getFirstName());
                        itemUserEntities.add(user);
                    }
                    listAdapter.updateData(itemUserEntities);
                    listAdapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.stateCentersLiveData.observe(getViewLifecycleOwner(), state -> {
            loadingCenters = state.isLoading();
            //Toast.makeText(this.getContext(), "vse", Toast.LENGTH_SHORT).show();
            binding.loading.setVisibility(Utils.visibleOrGone(loadingCenters));
            if (state.isLoading() == false) {
                if (state.getItemsVolunteerCenters() != null) {
                    volunteerCenters = state.getItemsVolunteerCenters();

                    List<String> volunteerCentersString =  new ArrayList<String>();
                    for (ItemVolunteerCenterEntity el : volunteerCenters) {
                        volunteerCentersString.add(el.getAddress());
                    }

                    ArrayAdapter<String> spinnerAdapter;
                    spinnerAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, volunteerCentersString);

                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    binding.spinner.setAdapter(spinnerAdapter);
                    //binding.spinner.setPromptId(R.string.chooseday);
                    //binding.spinner.setSelection(1, true);
                    String volunteerCenterId = preferenceManager.getString(Constants.KEY_VOLUNTEER_CENTER_ID);

                    if (volunteerCenterId != null) {
                        //Toast.makeText(this.getContext(), volunteerCenterId, Toast.LENGTH_SHORT).show();


                        binding.spinner.setSelection(Integer.parseInt(volunteerCenterId) - 1, true);
                    }


                } else {
                    Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }




            /*boolean isSuccess = !state.isLoading()
                    && state.getErrorMessage() == null
                    && state.getItems() != null;
            //binding.refresh.setEnabled(!state.isLoading());
            //if (!state.isLoading()) //binding.refresh.setRefreshing(false);
            binding.recycler.setVisibility(Utils.visibleOrGone(isSuccess));
            binding.error.setVisibility(Utils.visibleOrGone(state.getErrorMessage() != null));
            binding.loading.setVisibility(Utils.visibleOrGone(state.isLoading()));

            binding.error.setText(state.getErrorMessage());
            if (isSuccess) {
                listAdapter.updateData(state.getItems());
            }*/
        });
    }

    private void openProfile(@NonNull Integer id) {
        //TODO: add code here
    }

    @Override
    public void onDestroyView() {
        binding = null;
        preferenceManager.putString(Constants.KEY_VOLUNTEER_CENTER_ID, null);
        super.onDestroyView();
    }
}
