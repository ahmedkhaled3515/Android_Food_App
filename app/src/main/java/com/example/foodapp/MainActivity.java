package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navBar;
    NavController navController;
    public final static String SHARED_PREF_NAME="sharedPref";
    public final static String IS_LOGGED_FLAG="isLogged";
    public final static String LOGGED_EMAIL="email";
    FirebaseAuth mAuth;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navBar=findViewById(R.id.bottomNavigationView);
        Toolbar toolbar=findViewById(R.id.app_toolbar);
        mAuth=FirebaseAuth.getInstance();
        Menu menu= navBar.getMenu();
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        userEmail=sharedPreferences.getString(LOGGED_EMAIL,null);
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
                if(navDestination.getId() == R.id.loginFragment2 || navDestination.getId() == R.id.signupFragment)
                {
                    toolbar.setVisibility(View.GONE);
                }
                else {
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
                    toolbar.getMenu().findItem(R.id.menu_search).setVisible(true);
                    toolbar.getMenu().findItem(R.id.menu_search).setOnMenuItemClickListener(item -> {
                        navController.navigate(R.id.searchFragment);
                        return false;
                    });
                    if(navDestination.getId() == R.id.homeFragment)
                    {
                        String[] parts = userEmail.split("@");
                        toolbar.setTitle("Hello,"+parts[0]);
                        toolbar.setNavigationIcon(null);
                        toolbar.getMenu().findItem(R.id.menu_search).setVisible(false);
                    }
                    else if(navDestination.getId() == R.id.categoryMealsFragment)
                    {
                        toolbar.setTitle("Meals");
                    }
                    else if(navDestination.getId() == R.id.mealFragment)
                    {
                        toolbar.setTitle("Meal Details");
                    }
                    else if(navDestination.getId() == R.id.favoriteFragment)
                    {
                        toolbar.setTitle("Favorite Meals");
                        toolbar.setNavigationIcon(null);
                        toolbar.getMenu().findItem(R.id.menu_search).setVisible(false);
                    }
                    else if(navDestination.getId() == R.id.searchFragment)
                    {
                        toolbar.setTitle("Search");

                        toolbar.getMenu().findItem(R.id.menu_search).setVisible(false);
                    }
                    else if(navDestination.getId() == R.id.planFragment)
                    {
                        toolbar.setTitle("Schedule");
                        toolbar.setNavigationIcon(null);
                        toolbar.getMenu().findItem(R.id.menu_search).setVisible(false);
                    }
                    else if(navDestination.getId() == R.id.optionsFragment)
                    {
                        toolbar.setTitle("Options");
                        toolbar.setNavigationIcon(null);
                        toolbar.getMenu().findItem(R.id.menu_search).setVisible(false);
                    }





                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            navController.popBackStack();
                        }
                    });
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