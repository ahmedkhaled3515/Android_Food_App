package com.example.foodapp.modules.searchall.presenter;

import android.util.Log;

import com.example.foodapp.modules.searchall.view.SearchInterface;
import com.example.foodapp.network.AppRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchPresenter {
    AppRemoteDataSource appRemoteDataSource;
    SearchInterface searchInterface;
    public SearchPresenter(SearchInterface searchInterface)
    {
        appRemoteDataSource = AppRemoteDataSource.getInstance();
        this.searchInterface=searchInterface;
    }
    public void getSearch(String query)
    {
        Observable<String> ingredientsObservable=appRemoteDataSource.getIngredientsList();
        Observable<String> countriesObservable=appRemoteDataSource.getCountries()
                .flatMapIterable(foodCountryResponse -> foodCountryResponse.getMeals())
                .map(foodCountry -> foodCountry.getStrArea());
        Observable<String> categoriesObservable=appRemoteDataSource.getMealCategories()
                .flatMapIterable(foodCategoryResponse -> foodCategoryResponse.getList())
                .map(foodCategory -> foodCategory.getStrCategory());
        Observable<String> bigObservable= Observable.merge(ingredientsObservable,countriesObservable,categoriesObservable);
        bigObservable
                .filter(s -> s.toLowerCase().contains(query))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<String> stringList) {
                        Log.d("TAG", "onSuccess: "+stringList);
                        searchInterface.showResult(stringList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
