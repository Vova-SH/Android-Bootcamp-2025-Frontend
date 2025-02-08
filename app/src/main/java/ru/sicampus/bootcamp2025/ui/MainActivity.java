package ru.sicampus.bootcamp2025.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import ru.sicampus.bootcamp2025.R;
import ru.sicampus.bootcamp2025.databinding.ActivityMainBinding;
import ru.sicampus.bootcamp2025.ui.utils.MyNavigator;

public class MainActivity extends AppCompatActivity implements MyNavigator {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main);
//        navController = Navigation.findNavController(this, R.id.main);
        navController = navHostFragment.getNavController();


        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.authFragment || navDestination.getId() == R.id.registrationFragment) {
                binding.bottomNavigationView.setVisibility(View.GONE);
            } else {
                binding.bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });
//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.centersList) {
//                navController.navigate(R.id.centersList);
//            }
//            if (item.getItemId() == R.id.userProfileFragment) {
//                navController.navigate(R.id.userProfileFragment);
//            }
//            return true;
//        });

    }

    public void onLogout() {
        navController.navigate(R.id.action_volunteerProfile_to_registrationFragment);
        navController.clearBackStack(R.id.action_volunteerProfile_to_registrationFragment);
    }

}
