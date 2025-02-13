package ru.sicampus.bootcamp2025.ui.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding;
import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.ui.utils.Constants;
import ru.sicampus.bootcamp2025.ui.utils.PreferenceManager;
import ru.sicampus.bootcamp2025.ui.utils.Utils;

public class ProfileFragment extends Fragment {

    private static final String KEY_ID = "id";

    private FragmentProfileBinding binding;
    private PreferenceManager preferenceManager;
    private ProfileViewModel viewModel;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentProfileBinding.bind(view);
        preferenceManager = new PreferenceManager(this.getContext());
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            final FullUserEntity userEntity = state.getUser();
            final FullVolunteerCenterEntity volunteerCenterEntity = state.getVolunteerCenter();
            //if (userEntity == null) return; // TODO: handle error and loading
            if (state.isLoading() == false) {
                if (volunteerCenterEntity != null) {
                    binding.status.setVisibility(Utils.visibleOrGone(true));
                    binding.volunteerCenter.setVisibility(Utils.visibleOrGone(true));

                    binding.status.setText("Активен");
                    binding.volunteerCenter.setText(volunteerCenterEntity.getAddress());
                } else {
                    binding.status.setVisibility(Utils.visibleOrGone(true));
                    binding.volunteerCenter.setVisibility(Utils.visibleOrGone(false));

                    binding.status.setText("Неактивен");
                    ///binding.volunteerCenter.setText(volunteerCenterEntity.getAddress());
                }
                binding.lastName.setVisibility(Utils.visibleOrGone(userEntity.getLastName() != null));
                binding.firstName.setVisibility(Utils.visibleOrGone(userEntity.getFirstName() != null));
                binding.username.setVisibility(Utils.visibleOrGone(userEntity.getUsername() != null));
                binding.contactInfo.setVisibility(Utils.visibleOrGone(userEntity.getContactInfo() != null));
                binding.bio.setVisibility(Utils.visibleOrGone(userEntity.getBiography() != null));

                binding.lastName.setText(userEntity.getLastName());
                binding.firstName.setText(userEntity.getFirstName());
                binding.username.setText(userEntity.getUsername());
                binding.contactInfo.setText(userEntity.getContactInfo());
                binding.bio.setText(userEntity.getBiography());

            /*binding.name.setText(userEntity.getName());
            binding.username.setText(userEntity.getusername());
            binding.phone.setText(userEntity.getPhone());*/

            /*if (userEntity.getPhotoUrl() != null) {
                Picasso.get().load(userEntity.getPhotoUrl()).into(binding.image);
            }*/
            }
        });

        binding.close.setOnClickListener(view2 -> {
            //BottomNavigationView navigationView = view.getRootView().findViewById(R.id.nav_view); // selectBottomNavigationBarItem(actionId);
            //navigationView.setSelectedItemId(R.id.list);

            //view.getRootView().findViewById(R.id.nav_view).setVisibility(View.GONE);
            //Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_signInFragment);

            preferenceManager.clear();
            view.getRootView().findViewById(R.id.nav_view).setVisibility(View.GONE);
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_signInFragment);
        });

        viewModel.load(preferenceManager.getString(Constants.KEY_USER_USERNAME));

        /*Integer id = getArguments() != null ? getArguments().getInt(KEY_ID) : null;
        if (id == null) throw new IllegalStateException("ID is null");*/ //TODO
        //viewModel.load(id);

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
