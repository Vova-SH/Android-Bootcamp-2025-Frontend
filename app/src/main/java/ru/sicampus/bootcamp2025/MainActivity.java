package ru.sicampus.bootcamp2025;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.map:
                    onClickNavMap();
                    break;
                case R.id.list:
                    onClickNavList();
                    break;
                case R.id.profile:
                    onClickNavProfile();
                    break;
            }
            return true;
        });
    }

    private void onClickNavMap() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.root_fragment);
        NavController navController = navHostFragment.getNavController();
        //navHostFragment.getChildFragmentManager().getFragments().get(0);
        switch (navController.getCurrentDestination().getId()) {
            case R.id.listFragment:
                navController.navigate(R.id.action_listFragment_to_mapFragment);
                break;
            case R.id.profileFragment:
                navController.navigate(R.id.action_profileFragment_to_mapFragment);
                break;
        }
        Log.d("tagigi", navController.getCurrentDestination().getId() + "");
        Log.d("tagigi", R.id.listFragment + "");
                //.navigate(R.id.action_listFragment_to_mapFragment);
    }

    private void onClickNavList() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.root_fragment);
        NavController navController = navHostFragment.getNavController();
        switch (navController.getCurrentDestination().getId()) {
            case R.id.profileFragment:
                navController.navigate(R.id.action_profileFragment_to_listFragment);
                break;
            case R.id.mapFragment:
                navController.navigate(R.id.action_mapFragment_to_listFragment);
                break;
        }
    }

    private void onClickNavProfile() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.root_fragment);
        NavController navController = navHostFragment.getNavController();
        //navController.navigate(R.id.action_listFragment_to_profileFragment);
        switch (navController.getCurrentDestination().getId()) {
            case R.id.listFragment:
                navController.navigate(R.id.action_listFragment_to_profileFragment);
                break;
            case R.id.mapFragment:
                navController.navigate(R.id.action_mapFragment_to_profileFragment);
                break;
        }
    }
/*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}