package com.example.foodapp.modules.showmeal.presenter;

import android.content.Context;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plan;
import com.example.foodapp.model.database.MealLocalDataSourceImpl;
import com.example.foodapp.modules.showmeal.view.MealInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class MealPresenter{
    MealInterface mealInterface;
    MealLocalDataSourceImpl mealLocalDataSource;
    public MealPresenter(Context context,MealInterface mealInterface, String mealName)
    {
        this.mealInterface=mealInterface;
        AppRemoteDataSource appRemoteDataSource= AppRemoteDataSource.getInstance();
        mealLocalDataSource = MealLocalDataSourceImpl.getInstance(context);
        appRemoteDataSource.getMeals(mealName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsResponse -> {
                    mealInterface.showMealDetails(mealsResponse.getMealList().get(0));
                });
    }
    public void addToPlan(Plan plan)
    {
        mealLocalDataSource.addToPlan(plan);
    }
    public void addToFav(Meal meal)
    {
        mealLocalDataSource.insert(meal);
    }
    /*@Override
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

    }*/
}
