package ru.sicampus.bootcamp2025.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.FragmentMapBinding;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.ui.utils.Constants;
import ru.sicampus.bootcamp2025.ui.utils.PreferenceManager;

public class MapService implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {
    private Context context;
    private View view;
    PreferenceManager preferenceManager;

    public MapService(Context context, View view) {
        this.context = context;
        this.view = view;
        preferenceManager = new PreferenceManager(context);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {


    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        ItemVolunteerCenterEntity place = MapFragment.getVolunteerCentersName(marker.getTitle());


        preferenceManager.putString(Constants.KEY_VOLUNTEER_CENTER_ID, marker.getTitle());

        BottomNavigationView navigationView = view.getRootView().findViewById(R.id.nav_view); // selectBottomNavigationBarItem(actionId);
        navigationView.setSelectedItemId(R.id.list);

        //this.binding.getRoot().getRootView().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        //Navigation.findNavController(this.binding.getRoot().getRootView()).navigate(R.id.action_mapFragment_to_listFragment);
        // marker.getTitle()

        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(context, "FFFF", Toast.LENGTH_LONG).show();
            googleMap.setMyLocationEnabled(true);
            googleMap.setOnMyLocationButtonClickListener((GoogleMap.OnMyLocationButtonClickListener) this);
            googleMap.setOnMyLocationClickListener((GoogleMap.OnMyLocationClickListener) this);

        }


        googleMap.setOnMapClickListener(this);
        //googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerClickListener(this);
        MapFragment.getVolunteerCenters().forEach(p -> {
            googleMap.addMarker(new MarkerOptions().title(p.getId().toString()).position(new LatLng(Double.parseDouble(p.getLatitude()), Double.parseDouble(p.getLongitude()))));
        });


    }


}
