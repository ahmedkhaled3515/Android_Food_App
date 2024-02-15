package com.example.foodapp.modules.home.presenter;

import android.util.Log;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.home.view.HomeInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Query;

public class HomePresenter {
    HomeInterface homeInterface;
    AppRemoteDataSource appRemoteDataSource;
    public HomePresenter(HomeInterface homeInterface)
    {
        this.homeInterface= homeInterface;
        appRemoteDataSource=AppRemoteDataSource.getInstance();
        getCategories();
        getCountries();
        getRandomMeal();


    }

    private void getCategories()
    {
        appRemoteDataSource.getMealCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(foodCategoryResponse -> homeInterface.showCategories(foodCategoryResponse.getList()));
    }
    private void getCountries()
    {
        appRemoteDataSource.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(foodCountryResponse -> {
                    homeInterface.showCountries(foodCountryResponse.getMeals());
                });
    }
    private void getRandomMeal()
    {
        appRemoteDataSource.getRandomMeal().observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsResponse -> {
                    homeInterface.showRandomMeal(mealsResponse.getMealList().get(0));
                });
    }
//    @Override
//    public void onGetCategoriesSuccess(List<FoodCategory> categoryList) {
//        homeInterface.showCategories(categoryList);
//    }
//
//    @Override
//    public void onGetCategoriesFailure(Throwable error) {
//        homeInterface.showCategoriesError(error);
//    }
//
//    @Override
//    public void onGetCountriesSuccessful(List<FoodCountryResponse.FoodCountry> foodCountries) {
//        homeInterface.showCountries(foodCountries);
//    }
//
//    @Override
//    public void onGetCountriesFailure(Throwable throwable) {
//        homeInterface.showCountriesError(throwable);
//    }
//
//    @Override
//    public void onGetRandomMealSuccessful(Meal meal) {
//        homeInterface.showRandomMeal(meal);
//    }
//
//    @Override
//    public void onGetRandomMealFailure(Throwable throwable) {
//        homeInterface.showRandomMealError(throwable);
//    }
//
//    @Override
//    public void onGetMealSuccessful(List<Meal> mealList) {
//    }
//
//    @Override
//    public void onGetMealFailure(Throwable throwable) {
//
//    }
//
//    @Override
//    public void onGetMealsByCategorySuccessful(List<Meal> mealList) {
//
//    }
//
//    @Override
//    public void onGetMealsByCategoryFailure(Throwable t) {
//
//    }
}
