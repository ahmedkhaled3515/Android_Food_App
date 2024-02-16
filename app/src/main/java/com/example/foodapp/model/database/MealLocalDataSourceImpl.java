package com.example.foodapp.model.database;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource{
    MealDAO mealDAO;
    private static MealLocalDataSourceImpl instance=null;
    private MealLocalDataSourceImpl()
    {

    }
    public static MealLocalDataSourceImpl getInstance()
    {
        if(instance == null)
        {
            instance=new MealLocalDataSourceImpl();
        }
        return instance;
    }

    @Override
    public Flowable<List<Meal>> getFavMeals() {
        return null;
    }

    @Override
    public void insert(Meal meal) {

    }

    @Override
    public void delete(Meal meal) {

    }
}
