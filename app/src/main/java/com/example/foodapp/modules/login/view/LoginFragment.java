package com.example.foodapp.modules.login.view;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.MainActivity;
import com.example.foodapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {


    Button btnLogin;
    TextView tvSignupText;
    EditText etEmail;
    EditText etPassword;
    View view;
    FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;
    ImageView googleImg;
    TextView tvGuest;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK)
            {
                Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                try{
                    GoogleSignInAccount signInAccount = accountTask.getResult(ApiException.class);
                    AuthCredential authCredential=GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
                    mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getContext(), "User logged in successfully", Toast.LENGTH_SHORT).show();
                                if (signInAccount != null) {
                                    SharedPreferences sharedPreferences= getActivity().getSharedPreferences(MainActivity.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    editor.putBoolean(MainActivity.IS_LOGGED_FLAG,true);
                                    editor.putBoolean(MainActivity.IS_LOGGED_FLAG,true);
                                    Log.d("TAG", "onComplete: google "+task.getResult().getUser().getEmail());
                                    editor.putString(MainActivity.LOGGED_EMAIL,task.getResult().getUser().getEmail());
                                    editor.apply();
                                    // Navigate to home fragment
                                    Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
                                }
                            } else {
                                Toast.makeText(getContext(), "Authentication failed 1.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (ApiException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Authentication failed 2 .", Toast.LENGTH_SHORT).show();

                }
            }
            else {
                Toast.makeText(getContext(), "Authentication failed 3 .", Toast.LENGTH_SHORT).show();

            }
        }
    });

    @Override
    public void onStart() {
        super.onStart();
        if(isNetworkAvailable(this.getContext())) {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
            }
        }
        else {
            Toast.makeText(this.getContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences= getActivity().getSharedPreferences(MainActivity.SHARED_PREF_NAME,Context.MODE_PRIVATE);
            boolean isLogged= sharedPreferences.getBoolean(MainActivity.IS_LOGGED_FLAG,false);
            if(isLogged)
            {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_favoriteFragment);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
        googleImg=view.findViewById(R.id.googleLoginImg);
        tvSignupText.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_signupFragment);
        });
        FirebaseApp.initializeApp(getContext());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();
        GoogleSignIn.getClient(requireActivity(), gso);
        googleImg.setOnClickListener(v -> {
            Intent signInIntent = GoogleSignIn.getClient(requireActivity(), gso).getSignInIntent();
            activityResultLauncher.launch(signInIntent);
        });
        btnLogin.setOnClickListener(v -> {
//            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
            if(checkSyntax())
            {
                firebaseLogin(String.valueOf(etEmail.getText()),String.valueOf(etPassword.getText()));
            }
        });
        tvGuest=view.findViewById(R.id.tvGuest);
        if(isNetworkAvailable(getContext()))
        {

            tvGuest.setOnClickListener(v -> {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
            });
        }
        else {
            tvGuest.setAlpha(0.5f);
        }

        return view;
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
                            SharedPreferences sharedPreferences= getActivity().getSharedPreferences(MainActivity.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor= sharedPreferences.edit();
                            editor.putBoolean(MainActivity.IS_LOGGED_FLAG,true);
                            Log.d("TAG", "onComplete: "+task.getResult().getUser().getEmail());
                            editor.putString(MainActivity.LOGGED_EMAIL,task.getResult().getUser().getEmail());
                            editor.apply();
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_homeFragment);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("TAG", "onComplete: login failed ");
                        }
                    }
                });
    }
}