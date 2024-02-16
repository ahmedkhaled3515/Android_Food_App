package com.example.foodapp.modules.favorite.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.model.Meal;


public class FavoriteFragment extends Fragment implements IFavourite,OnFavoriteClickListener {


    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_favorite, container, false);

        return view;
    }

    @Override
    public void onViewClick(Meal meal) {

    }

    @Override
    public void onRemoveClick(Meal meal) {

    }
}