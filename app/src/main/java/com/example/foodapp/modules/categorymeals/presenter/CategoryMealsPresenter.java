package com.example.foodapp.modules.categorymeals.presenter;

import android.util.Log;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.categorymeals.view.CategoryMealsInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

public class CategoryMealsPresenter implements NetworkCallBack {
    CategoryMealsInterface categoryMealsInterface;
    public CategoryMealsPresenter(CategoryMealsInterface categoryMealsInterface,String category)
    {
        this.categoryMealsInterface=categoryMealsInterface;
        AppRemoteDataSource appRemoteDataSource=AppRemoteDataSource.getInstance();
        appRemoteDataSource.setNetworkCallBack(this);
        appRemoteDataSource.getMealsByCategory(category);
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

    }

    @Override
    public void onGetMealFailure(Throwable throwable) {

    }

    @Override
    public void onGetMealsByCategorySuccessful(List<Meal> mealList) {
        Log.i("TAG", "onGetMealsByCategorySuccessful:dddddddddddddddd ");
        categoryMealsInterface.showMeals(mealList);
    }

    @Override
    public void onGetMealsByCategoryFailure(Throwable t) {
        t.printStackTrace();
        categoryMealsInterface.showMealsError(t);
    }
}
