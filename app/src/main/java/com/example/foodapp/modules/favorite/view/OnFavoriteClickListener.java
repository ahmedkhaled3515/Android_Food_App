package com.example.foodapp.modules.favorite.view;

import com.example.foodapp.model.Meal;

public interface OnFavoriteClickListener {
    public void onViewClick(Meal meal);
    public void onRemoveClick(Meal meal);
}
