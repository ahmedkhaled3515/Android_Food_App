package com.example.foodapp.modules.categorymeals.view;

import com.example.foodapp.model.Meal;

public interface MealsClickListener {
    public void onViewClickListener(Meal clickedMeal);
    public void onFavoriteClickListener(Meal clickedMeal);
}
