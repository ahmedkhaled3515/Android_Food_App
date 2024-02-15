package com.example.foodapp.modules.login.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {



    FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    Button btnLogin;
    TextView tvSignupText;
    EditText etEmail;
    EditText etPassword;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login, container, false);
        mAuth=FirebaseAuth.getInstance();
        etEmail=view.findViewById(R.id.etEmail);
        etPassword=view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvSignupText=view.findViewById(R.id.tvSignup);
        tvSignupText.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_signupFragment);
        });
        btnLogin.setOnClickListener(v -> {
//            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
            if(checkSyntax())
            {
                firebaseLogin(String.valueOf(etEmail.getText()),String.valueOf(etPassword.getText()));
            }
        });
        return view;
    }
    private boolean checkSyntax()
    {
        String email=String.valueOf(etEmail.getText());
        String password=String.valueOf(etPassword.getText());
        boolean flag=true;
        if(email.isEmpty())
        {
            etEmail.setError("Email field can't be empty");
            flag=false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etEmail.setError("Email is not valid");
            flag=false;
        }
        if(password.isEmpty())
        {
            etPassword.setError("Password can't be empty");
            flag=false;
        }
        return flag;
    }
    private void firebaseLogin(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("TAG", "onComplete: login failed ");
                        }
                    }
                });
    }
}