package com.example.foodapp.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodapp.model.Meal;

@Database(entities = {Meal.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance=null;

    private AppDataBase(){

    }
    public AppDataBase getInstance(Context context)
    {
        if(instance == null)
        {
            instance= Room.databaseBuilder(context,AppDataBase.class,"foodappdb").build();
        }
        return instance;
    }
}
