package com.example.foodapp.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plan;

import java.sql.Date;

@Database(entities = {Meal.class, Plan.class},version = 4)
@TypeConverters({SqlDateConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance=null;
    public abstract MealDAO getMealDAO();
    public abstract PlanDAO getPlanDAO();
    public static AppDataBase getInstance(Context context)
    {
        if(instance == null)
        {
            instance= Room.databaseBuilder(context,AppDataBase.class,"foodappdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
class SqlDateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

