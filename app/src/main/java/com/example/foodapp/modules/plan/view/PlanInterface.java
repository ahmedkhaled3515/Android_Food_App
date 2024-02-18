package com.example.foodapp.modules.plan.view;

import com.example.foodapp.model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlanInterface {
    public void showPlans(Flowable<List<Plan>> planFlowable);
}
