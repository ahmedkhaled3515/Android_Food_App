package com.example.foodapp.modules.showmeal.view;

import com.example.foodapp.model.Meal;

public interface MealInterface {
    public void showMealDetails(Meal meal);
    public void showMealDetailsError(Throwable t);
}
