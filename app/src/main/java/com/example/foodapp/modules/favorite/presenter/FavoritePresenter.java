package com.example.foodapp.modules.favorite.presenter;

import android.content.Context;

import com.example.foodapp.model.Meal;
import com.example.foodapp.model.database.MealLocalDataSourceImpl;
import com.example.foodapp.modules.favorite.view.IFavourite;
import com.example.foodapp.modules.home.view.HomeInterface;

public class FavoritePresenter {
    MealLocalDataSourceImpl mealLocalDataSource;
    IFavourite favouriteInterface;
    public FavoritePresenter(Context context,IFavourite favouriteInterface)
    {
        this.favouriteInterface=favouriteInterface;
        mealLocalDataSource=MealLocalDataSourceImpl.getInstance(context);
        showFavMeals();
    }
    public void removeFromFav(Meal meal)
    {
        mealLocalDataSource.delete(meal);
    }
    public void showFavMeals()
    {
        favouriteInterface.showMeals(mealLocalDataSource.getFavMeals());
    }


}
