package com.example.foodapp.modules.searchall.presenter;

import android.util.Log;

import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.SearchResult;
import com.example.foodapp.modules.searchall.view.SearchInterface;
import com.example.foodapp.network.AppRemoteDataSource;

import java.sql.ResultSet;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    AppRemoteDataSource appRemoteDataSource;
    SearchInterface searchInterface;
    Observable<String> ingredientsObservable;
    Observable<FoodCountryResponse> countriesObservable;
    Observable<FoodCategoryResponse> categoriesObservable;
    Observable<SearchResult> mealsObservable;
    public SearchPresenter(SearchInterface searchInterface)
    {
        appRemoteDataSource = AppRemoteDataSource.getInstance();
        this.searchInterface=searchInterface;
        loadData();
    }
    public void loadData()
    {
        ingredientsObservable=appRemoteDataSource.getIngredientsList();
        countriesObservable=appRemoteDataSource.getCountries();
        categoriesObservable=appRemoteDataSource.getMealCategories();

    }
    public void getSearch(String query)
    {
        Observable<SearchResult> obs1=ingredientsObservable
            .map(s -> new SearchResult(s,"ingredient"))
                .subscribeOn(Schedulers.io());
        Observable<SearchResult> obs2=countriesObservable
                .flatMapIterable(foodCountryResponse -> foodCountryResponse.getMeals())
                .map(foodCountry -> new SearchResult(foodCountry.getStrArea(),"country"))
                .subscribeOn(Schedulers.io());
        Observable<SearchResult> obs3=categoriesObservable
                .flatMapIterable(foodCategoryResponse -> foodCategoryResponse.getList())
                .map(foodCategory -> new SearchResult(foodCategory.getStrCategory(),"category"))
                .subscribeOn(Schedulers.io());
        Observable<SearchResult> mealsObservable= appRemoteDataSource.getMeals(query)
                .flatMapIterable(mealsResponse -> mealsResponse.getMealList())
                .map(meal -> new SearchResult(meal.getStrMeal(),"meal"))
                .subscribeOn(Schedulers.io());
        Observable<SearchResult> bigObservable= Observable.merge(obs1,obs2,obs3,mealsObservable);
        bigObservable
                .filter(s -> s.getResult().toLowerCase().contains(query))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchResult>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<SearchResult> stringList) {
                        Log.d("TAG", "onSuccess: "+stringList);
                        searchInterface.showResult(stringList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
