package com.example.foodapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodapp.model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;

@Dao
public interface PlanDAO {
    @Query("select * from meal_plan")
    public Flowable<List<Plan>> getAllPlans();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addToPlan(Plan plan);
    @Delete
    public void removeFromPlan(Plan plan);


}
