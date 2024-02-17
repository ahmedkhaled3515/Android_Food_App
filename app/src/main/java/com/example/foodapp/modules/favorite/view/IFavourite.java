package com.example.foodapp.modules.favorite.view;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface IFavourite {
    public void showMeals(Flowable<List<Meal>> mealFlowable);
}
