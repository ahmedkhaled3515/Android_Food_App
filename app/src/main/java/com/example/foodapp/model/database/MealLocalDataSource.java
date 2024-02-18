package com.example.foodapp.model.database;

import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.flow.Flow;

public interface MealLocalDataSource {
    public Flowable<List<Meal>> getFavMeals();
    public void insert(Meal meal);
    public void delete(Meal meal);
    public Flowable<List<Plan>> getPlans();
    public void addToPlan(Plan plan);
    public void removeFromPlan(Plan plan);
}
