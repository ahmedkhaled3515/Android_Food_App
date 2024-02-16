package com.example.foodapp.model.database;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {
    public Flowable<List<Meal>> getFavMeals();
    public void insert(Meal meal);
    public void delete(Meal meal);
}
