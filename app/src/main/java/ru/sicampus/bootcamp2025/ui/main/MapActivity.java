package com.example.myapplication.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    ImageButton toMap, toProfile, toNotif, toIvents;
    private MapView mapView;
    private List<GeoPoint> volunteerCenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(14);

        volunteerCenters = new ArrayList<>();
        volunteerCenters.add(new GeoPoint(55.751244, 37.618423));
        volunteerCenters.add(new GeoPoint(55.733338, 37.588146));
        volunteerCenters.add(new GeoPoint(55.770394, 37.613150));
        volunteerCenters.add(new GeoPoint(55.760774, 37.616615));

        addMarkers();

        // Устанавка камеры на маркер 1
        mapController.setCenter(volunteerCenters.get(0));



        toMap=findViewById(R.id.bt_map);
        toProfile=findViewById(R.id.bt_profile);
        toNotif=findViewById(R.id.bt_notif);
        toIvents=findViewById(R.id.bt_ivents);

        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

        toIvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),IventsActivity.class));
            }
        });
    }

    private void addMarkers() {
        for (int i = 0; i < volunteerCenters.size(); i++) {
            Marker marker = new Marker(mapView);
            marker.setPosition(volunteerCenters.get(i));
            marker.setTitle("Волонтерский центр " + (i + 1));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            //marker.setIcon();
            marker.setRelatedObject(i);
            marker.setIcon(getResources().getDrawable(R.drawable.marker_icon, null)); // иконку маркера
            marker.setSubDescription("Описание центра");
            marker.showInfoWindow();
            mapView.getOverlays().add(marker);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDetach();
    }
}