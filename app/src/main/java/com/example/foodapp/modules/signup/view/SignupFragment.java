package com.example.foodapp.modules.signup.view;

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
import android.widget.Toast;

import com.example.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;


public class SignupFragment extends Fragment {




    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordConfirm;
    Button btnSignup;
    public static final Pattern EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    private static final String PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*+=_-])[A-Za-z\\d@#$%^&*+=_-]{8,}$";


//    private static final Pattern  Password_Pattern = Pattern.compile(PATTERN);
    private static final Pattern  Password_Pattern = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
            "$");

    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_signup, container, false);
        mAuth= FirebaseAuth.getInstance();
        etName=view.findViewById(R.id.etName);
        etEmail=view.findViewById(R.id.etEmail);
        etPassword=view.findViewById(R.id.etPassword);
        etPasswordConfirm=view.findViewById(R.id.etPasswordConfirm);
        btnSignup=view.findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(v -> {
           if(verifySyntax())
           {
                createUser(String.valueOf(etEmail.getText()),String.valueOf(etPassword.getText()));
           }
        });
        return view;
    }
    private boolean verifySyntax()
    {
        String name=String.valueOf(etName.getText());
        String email=String.valueOf(etEmail.getText());
        String password=String.valueOf(etPassword.getText());
        String passwordConfirm=String.valueOf(etPasswordConfirm.getText());
        boolean flag=true;
        if(name.isEmpty())
        {
            etName.setError("Name field is empty");
            Toast.makeText(this.getContext(),"Name field is empty",Toast.LENGTH_SHORT).show();
            flag= false;
        }
        if(email.isEmpty())
        {
            etEmail.setError("Email field is empty");
            flag= false;

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etEmail.setError("Email is not valid");
            flag= false;

        }

        if(password.isEmpty())
        {
            etPassword.setError("Password can't be empty");
            flag= false;

        }
        else if(!Password_Pattern.matcher(password).matches())
        {
            etPassword.setError("Password is weak");
            flag= false;

        }
        if (!passwordConfirm.equals(password)) {
            etPasswordConfirm.setError("Password is not matched");
            flag= false;

        }
        return flag;
    }
    private void createUser(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment2);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());

                        }
                    }
                });

    }
}