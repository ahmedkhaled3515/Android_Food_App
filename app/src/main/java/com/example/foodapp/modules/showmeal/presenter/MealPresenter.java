package com.example.foodapp.modules.showmeal.presenter;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.showmeal.view.MealInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

public class MealPresenter implements NetworkCallBack {
    MealInterface mealInterface;
    public MealPresenter(MealInterface mealInterface)
    {
        this.mealInterface=mealInterface;
        AppRemoteDataSource appRemoteDataSource= AppRemoteDataSource.getInstance();
        appRemoteDataSource.setNetworkCallBack(this);
        appRemoteDataSource.getMeals("Arrabiata");
    }
    @Override
    public void onGetCategoriesSuccess(List<FoodCategory> categoryList) {

    }

    @Override
    public void onGetCategoriesFailure(Throwable error) {

    }

    @Override
    public void onGetCountriesSuccessful(List<FoodCountryResponse.FoodCountry> foodCountries) {

    }

    @Override
    public void onGetCountriesFailure(Throwable throwable) {

    }

    @Override
    public void onGetRandomMealSuccessful(Meal meal) {

    }

    @Override
    public void onGetRandomMealFailure(Throwable throwable) {

    }

    @Override
    public void onGetMealSuccessful(List<Meal> mealList) {
        mealInterface.showMealDetails(mealList.get(0));
    }

    @Override
    public void onGetMealFailure(Throwable throwable) {
        mealInterface.showMealDetailsError(throwable);
    }

    @Override
    public void onGetMealsByCategorySuccessful(List<Meal> mealList) {

    }

    @Override
    public void onGetMealsByCategoryFailure(Throwable t) {

    }
}