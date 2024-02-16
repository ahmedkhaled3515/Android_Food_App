package com.example.foodapp.model.database;

import android.content.Context;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource{
    private MealDAO mealDAO;
    private static MealLocalDataSourceImpl instance=null;
    private MealLocalDataSourceImpl(Context context)
    {
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        mealDAO = appDataBase.getMealDAO();

    }
    public static MealLocalDataSourceImpl getInstance(Context context)
    {
        if(instance == null)
        {
            instance=new MealLocalDataSourceImpl(context);
        }
        return instance;
    }

    @Override
    public Flowable<List<Meal>> getFavMeals() {
        return mealDAO.getFavoriteMeals();
    }

    @Override
    public void insert(Meal meal) {
        new Thread(() -> {
            mealDAO.insertToFavorite(meal);
        }).start();
    }

    @Override
    public void delete(Meal meal) {
        new Thread(() -> {
            mealDAO.delete(meal);
        }).start();
    }
}
