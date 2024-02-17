package com.example.foodapp.modules.favorite.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_favorite, container, false);
        favoriteRecycler=view.findViewById(R.id.favoriteRecycler);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favoriteRecycler.setLayoutManager(linearLayoutManager);
        favoritePresenter=new FavoritePresenter(this.getContext(),this);


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
        TextView tvEmptyData=view.findViewById(R.id.tvEmptyData);
        mealFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealList -> {
                    if(mealList != null) {

                        mealList.removeIf(meal -> !meal.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()));
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