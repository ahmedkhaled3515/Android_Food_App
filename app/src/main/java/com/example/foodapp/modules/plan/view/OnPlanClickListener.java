package com.example.foodapp.modules.plan.view;

import com.example.foodapp.model.Plan;

public interface OnPlanClickListener {
    public void onRemoveClick(Plan plan);
    public void onViewClick(String mealName);

}
