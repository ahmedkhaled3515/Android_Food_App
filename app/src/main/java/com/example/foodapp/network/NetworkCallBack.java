package com.example.foodapp.network;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;

import java.util.List;

public interface NetworkCallBack {
    public void onGetCategoriesSuccess(List<FoodCategory> categoryList);
    public void onGetCategoriesFailure(Throwable error);
    public void onGetCountriesSuccessful(List<FoodCountryResponse.FoodCountry> foodCountries);
    public void onGetCountriesFailure(Throwable throwable);
    public void onGetRandomMealSuccessful(Meal meal);
    public void onGetRandomMealFailure(Throwable throwable);
}
