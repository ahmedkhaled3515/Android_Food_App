package com.example.foodapp.network;

import android.util.Log;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRemoteDataSource {
    Retrofit retrofit;
    Network network;
    NetworkCallBack networkCallBack;
    final static String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static AppRemoteDataSource instance = null;
    private AppRemoteDataSource(NetworkCallBack networkCallBack){
        this.networkCallBack=networkCallBack;
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        network=retrofit.create(Network.class);
    }
    public static AppRemoteDataSource getInstance(NetworkCallBack networkCallBack) {
        if(instance == null){
            instance=new AppRemoteDataSource(networkCallBack);
        }
        return instance;
    }
    public void getMealCategories()
    {
        Log.i("TAG", "getMealCategories: dddddddddddddddddddddddd");
        Call<FoodCategoryResponse> call= network.getCategories();
        call.enqueue(new Callback<FoodCategoryResponse>() {
            @Override
            public void onResponse(Call<FoodCategoryResponse> call, Response<FoodCategoryResponse> response) {
                Log.d("TAG", "onResponse: ddddddddddddd"+response.body().getList().size());
                FoodCategoryResponse foodCategoryResponse = response.body();
                networkCallBack.onGetCategoriesSuccess(foodCategoryResponse.getList());
            }

            @Override
            public void onFailure(Call<FoodCategoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void getCountries()
    {
        Call<FoodCountryResponse> call= network.getCountries();
        call.enqueue(new Callback<FoodCountryResponse>() {
            @Override
            public void onResponse(Call<FoodCountryResponse> call, Response<FoodCountryResponse> response) {

                networkCallBack.onGetCountriesSuccessful(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<FoodCountryResponse> call, Throwable t) {
                networkCallBack.onGetCountriesFailure(t);
            }
        });
    }
    public void getRandomMeal()
    {
        Call<MealsResponse> call=network.getMealOfTheDay();
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.d("TAG", "onResponse:ffffffffffffffff "+response.body());
                networkCallBack.onGetRandomMealSuccessful(response.body().getMealList().get(0));
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallBack.onGetRandomMealFailure(t);
            }
        });
    }
    public void getMeals()
    {
        Call<MealsResponse> call = network.getMeals();
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i("TAG", "onResponse: ddddddd" + response.body().getMealList().size() );
                networkCallBack.onGetMealsSuccessful(response.body().getMealList());
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallBack.onGetMealsFailure(t);
            }
        });
    }
    public void getMealsByCategory(String category)
    {
        Call<MealsResponse> call = network.getMealsByCategory(category);
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i("TAG", "onResponse: "+ response.body().getMealList().size());
                networkCallBack.onGetMealsByCategorySuccessful(response.body().getMealList());
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
