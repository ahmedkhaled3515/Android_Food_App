package com.example.foodapp.modules.categorymeals.presenter;

import android.util.Log;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;
import com.example.foodapp.modules.categorymeals.view.MealsListInterface;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MealsListPresenter{
    MealsListInterface mealsListInterface;
    AppRemoteDataSource appRemoteDataSource;

    public MealsListPresenter(MealsListInterface mealsListInterface)
    {
        this.mealsListInterface = mealsListInterface;
        appRemoteDataSource=AppRemoteDataSource.getInstance();
    }
    public void getMealsByIngredients(String ingredient)
    {
        Log.d("TAG", "getMealsByIngredients: "+ingredient);
//        appRemoteDataSource.getMealsByIngredients(ingredient)
//                .doOnNext(mealsResponse -> Log.d("TAG", "getMealsByIngredients: "+mealsResponse.getMealList()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mealsResponse -> {
//                    mealsListInterface.showMeals(mealsResponse.getMealList());
//                }
//                );
        appRemoteDataSource.getMealsByIngredients(ingredient)
                .doOnNext(mealsResponse -> Log.d("TAG", "getMealsByIngredients: "+mealsResponse.getMealList()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
                        mealsListInterface.showMeals(mealsResponse.getMealList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getMealsByCategory(String category)
    {
        appRemoteDataSource.getMealsByCategory(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsResponse -> {
                    mealsListInterface.showMeals(mealsResponse.getMealList());
                });
    }
    public void getMealsByCountry(String country)
    {
        appRemoteDataSource.getMealsByCountry(country).observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsResponse -> {
                    mealsListInterface.showMeals(mealsResponse.getMealList());
                });
    }

//    @Override
//    public void onGetCategoriesSuccess(List<FoodCategory> categoryList) {
//
//    }
//
//    @Override
//    public void onGetCategoriesFailure(Throwable error) {
//
//    }
//
//    @Override
//    public void onGetCountriesSuccessful(List<FoodCountryResponse.FoodCountry> foodCountries) {
//
//    }
//
//    @Override
//    public void onGetCountriesFailure(Throwable throwable) {
//
//    }
//
//    @Override
//    public void onGetRandomMealSuccessful(Meal meal) {
//
//    }
//
//    @Override
//    public void onGetRandomMealFailure(Throwable throwable) {
//
//    }
//
//    @Override
//    public void onGetMealSuccessful(List<Meal> mealList) {
//
//    }
//
//    @Override
//    public void onGetMealFailure(Throwable throwable) {
//
//    }
//
//    @Override
//    public void onGetMealsByCategorySuccessful(List<Meal> mealList) {
//        Log.i("TAG", "onGetMealsByCategorySuccessful:dddddddddddddddd ");
//        mealsListInterface.showMeals(mealList);
//    }
//
//    @Override
//    public void onGetMealsByCategoryFailure(Throwable t) {
//        t.printStackTrace();
//        mealsListInterface.showMealsError(t);
//    }
}
