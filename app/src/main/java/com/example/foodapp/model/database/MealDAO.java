package com.example.foodapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodapp.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
    @Query("select * from favorite_meal")
    public Flowable<List<Meal>> getFavoriteMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertToFavorite(Meal meal);
    @Delete
    public void delete(Meal meal);
}
