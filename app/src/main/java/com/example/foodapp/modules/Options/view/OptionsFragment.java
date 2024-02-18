package com.example.foodapp.modules.Options.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodapp.MainActivity;
import com.example.foodapp.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionsFragment extends Fragment {


    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            view=inflater.inflate(R.layout.no_access_layout,container,false);
            Button btnGoSignIn=view.findViewById(R.id.btnSignInPage);
            btnGoSignIn.setOnClickListener(v -> {

                Navigation.findNavController(view).navigate(R.id.loginFragment2);
            });
        }
        else{
            view = inflater.inflate(R.layout.fragment_options, container, false);
            Button btnLogout=view.findViewById(R.id.btnLogoutOption);
            btnLogout.setOnClickListener(v -> {
                auth.signOut();
                SharedPreferences sharedPreferences= getActivity().getSharedPreferences(MainActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean(MainActivity.IS_LOGGED_FLAG,false);
                editor.apply();
                Navigation.findNavController(view).navigate(R.id.action_optionsFragment_to_loginFragment2);
            });
        }
        return view;
    }
}