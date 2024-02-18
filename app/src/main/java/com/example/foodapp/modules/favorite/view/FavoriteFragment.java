package com.example.foodapp.modules.favorite.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.MainActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.favorite.presenter.FavoritePresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavoriteFragment extends Fragment implements IFavourite,OnFavoriteClickListener {


    View view;
    RecyclerView favoriteRecycler;
    FavoriteRecyclerAdapter favoriteAdapter;
    FavoritePresenter favoritePresenter;
    String email;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences(MainActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean(MainActivity.IS_LOGGED_FLAG,false);
        email=sharedPreferences.getString(MainActivity.LOGGED_EMAIL,null);
        if(FirebaseAuth.getInstance().getCurrentUser()==null && !isLogged)
        {
            view=inflater.inflate(R.layout.no_access_layout,container,false);
            Button btnGoSignIn=view.findViewById(R.id.btnSignInPage);
            btnGoSignIn.setOnClickListener(v -> {
                Navigation.findNavController(view).navigate(R.id.loginFragment2);
            });
        }
        else
        {
                view = inflater.inflate(R.layout.fragment_favorite, container, false);
                favoriteRecycler = view.findViewById(R.id.favoriteRecycler);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                favoriteRecycler.setLayoutManager(linearLayoutManager);
                favoritePresenter = new FavoritePresenter(this.getContext(), this);
        }


        return view;
    }

    @Override
    public void onViewClick(Meal meal) {
        Bundle bundle= new Bundle();
        bundle.putString("type","meal");
        bundle.putString("meal",meal.getStrMeal());
        Navigation.findNavController(view).navigate(R.id.action_favoriteFragment_to_mealFragment,bundle);
    }

    @Override
    public void onRemoveClick(Meal meal) {
        favoritePresenter.removeFromFav(meal);
    }

    @Override
    public void showMeals(Flowable<List<Meal>> mealFlowable) {
        Log.d("TAG", "showMeals: "+ email);
        TextView tvEmptyData=view.findViewById(R.id.tvEmptyData);
        mealFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealList -> {
                    if(mealList != null) {
                        mealList.removeIf(meal -> !meal.getEmail().equals(email));
                        favoriteAdapter = new FavoriteRecyclerAdapter(this.getContext(), this, mealList);
                        favoriteRecycler.setAdapter(favoriteAdapter);
                        tvEmptyData.setVisibility(View.GONE);
                        favoriteRecycler.setVisibility(View.VISIBLE);
                    }
                    else {
                        tvEmptyData.setVisibility(View.VISIBLE);
                        favoriteRecycler.setVisibility(View.GONE);

                    }
                });
    }
}