package ru.sicampus.bootcamp2025.ui.map;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.FragmentMapBinding;
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding;
import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.ui.profile.MapViewModel;
import ru.sicampus.bootcamp2025.ui.utils.Utils;

public class MapFragment extends Fragment {

    private static final String KEY_ID = "id";

    private FragmentMapBinding binding;
    private MapViewModel viewModel;
    private static List<ItemVolunteerCenterEntity> volunteerCenters = new ArrayList<>();
    //private static List<Place> places = new ArrayList<>();
    public MapFragment() {
        super(R.layout.fragment_map);
    }

//    public getViewFr() {
//        return
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMapBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(MapViewModel.class);
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
            if (state.isLoading() == false) {
                if (state.getItems() != null) {
                    volunteerCenters = state.getItems();
                    /*places = new ArrayList<>();
                    for (ItemVolunteerCenterEntity vc : volunteerCenters) {
                        Place place = new Place(vc.getId(), new LatLng(Double.parseDouble(vc.getLatitude()), Double.parseDouble(vc.getLongitude())));
                        places.add(place);
                    }*/

                    //int actionId = getNavigationMenuItemId();

                    //view.getRootView().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);

//                    BottomNavigationView navigationView = view.getRootView().findViewById(R.id.nav_view); // selectBottomNavigationBarItem(actionId);
//                    navigationView.setSelectedItemId(R.id.list);

                    SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view); // getParentFragmentManager().findFragmentById(R.id.map_view);

                    supportMapFragment.getMapAsync(new MapService(this.getContext(), view));
                } else {
                    Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Integer id = getArguments() != null ? getArguments().getInt(KEY_ID) : null;
        //if (id == null) throw new IllegalStateException("ID is null");

        viewModel.load();

    }
    public static List<ItemVolunteerCenterEntity> getVolunteerCenters(){
        return volunteerCenters;
    }
    public static ItemVolunteerCenterEntity getVolunteerCentersName(String name){
        for(ItemVolunteerCenterEntity p: volunteerCenters){
            if (Objects.equals(name, p.getId().toString())){
                return p;
            }
        }
        return null;
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
