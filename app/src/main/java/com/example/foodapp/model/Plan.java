package com.example.foodapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.sql.Date;

@Entity(tableName = "meal_plan",primaryKeys = {"idMeal","email","date"})
public class Plan {

    @NonNull
    String idMeal;
    @NonNull
    String email;
    String strMeal;
    String strMealThumb;
    @NonNull
    Date date;

    public Plan(@NonNull String idMeal, @NonNull String email, String strMeal, String strMealThumb, Date date) {
        this.idMeal = idMeal;
        this.email = email;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.date = date;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
