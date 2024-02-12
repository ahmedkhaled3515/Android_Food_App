package com.example.foodapp.modules.login.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    Button btnLogin;
    TextView tvSignupText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvSignupText=view.findViewById(R.id.tvSignup);
        tvSignupText.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_signupFragment);
        });
        btnLogin.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
        });
        return view;
    }
}