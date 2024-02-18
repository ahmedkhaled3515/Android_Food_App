package com.example.foodapp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.widget.Toast;

import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource{
    private MealDAO mealDAO;
    private PlanDAO planDAO;
    private static MealLocalDataSourceImpl instance=null;
    Context context;
    private MealLocalDataSourceImpl(Context context)
    {
        this.context=context;
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        mealDAO = appDataBase.getMealDAO();
        planDAO= appDataBase.getPlanDAO();

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
//            try{
                mealDAO.insertToFavorite(meal);
//            }
//            catch (SQLiteConstraintException e)
//            {
//                e.printStackTrace();
//                Toast.makeText(context,"Meal already exists in the favorites",Toast.LENGTH_SHORT).show();
//            }

        }).start();
    }

    @Override
    public void delete(Meal meal) {
        new Thread(() -> {
            mealDAO.delete(meal);
        }).start();
    }

    @Override
    public Flowable<List<Plan>> getPlans() {
        return planDAO.getAllPlans();
    }

    @Override
    public void addToPlan(Plan plan) {
        new Thread(() -> {
            planDAO.addToPlan(plan);
        }).start();
    }

    @Override
    public void removeFromPlan(Plan plan) {
        new Thread(() -> {
            planDAO.removeFromPlan(plan);
        }).start();
    }
}
