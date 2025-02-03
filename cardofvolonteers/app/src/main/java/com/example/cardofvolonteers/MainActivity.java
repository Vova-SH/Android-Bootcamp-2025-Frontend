package com.example.cardofvolonteers;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends AppCompatActivity {
    public static NavOptions options;
    public static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        NavOptions.Builder builder = new NavOptions.Builder();
        builder.setLaunchSingleTop(true);
        options = builder.build();
        assert  navHostFragment != null;
        navController = navHostFragment.getNavController();
    }

}