package com.example.foodapp.modules.categorymeals.view;

import com.example.foodapp.model.Meal;

import java.util.List;

public interface MealsListInterface {
    public void showMeals(List<Meal> mealList);
    public void showMealsError(Throwable t);
}
