package com.example.foodapp.modules.plan.presenter;

import android.content.Context;

import com.example.foodapp.model.database.MealLocalDataSourceImpl;
import com.example.foodapp.modules.plan.view.PlanInterface;

public class PlanPresenter {
    PlanInterface planInterface;
    MealLocalDataSourceImpl mealLocalDataSource;

    public PlanPresenter(Context context, PlanInterface planInterface)
    {
        mealLocalDataSource = MealLocalDataSourceImpl.getInstance(context);
        this.planInterface=planInterface;
        getAllPlans();
    }
    public void getAllPlans()
    {
        planInterface.showPlans(mealLocalDataSource.getPlans());
    }
}
