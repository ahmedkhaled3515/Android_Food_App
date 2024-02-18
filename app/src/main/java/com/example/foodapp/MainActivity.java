package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navBar;
    NavController navController;
    public final static String SHARED_PREF_NAME="sharedPref";
    public final static String IS_LOGGED_FLAG="isLogged";
    public final static String LOGGED_EMAIL="email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navBar=findViewById(R.id.bottomNavigationView);
        Menu menu= navBar.getMenu();
        navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(navBar,navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId() == R.id.homeFragment || navDestination.getId() == R.id.favoriteFragment || navDestination.getId() == R.id.optionsFragment || navDestination.getId() == R.id.searchFragment || navDestination.getId() == R.id.planFragment)
                {
//                    navBar.setActivated(false);
//                    navBar.setEnabled(false);
                    navBar.setVisibility(View.VISIBLE);
                }
                else {
//                    navBar.setActivated(true);
//                    navBar.setEnabled(true);
                    navBar.setVisibility(View.GONE);
                }

            }
        });

        if(!isNetworkAvailable(this))
        {
            menu.findItem(R.id.homeFragment).setVisible(false);
            menu.findItem(R.id.searchFragment).setVisible(false);
            menu.findItem(R.id.optionsFragment).setVisible(false);
        }
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}