package com.example.foodapp.network;

import android.util.Log;

import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCategoryResponse;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealsResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRemoteDataSource {
    Retrofit retrofit;
    Network network;
    NetworkCallBack  networkCallBack;
    final static String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static AppRemoteDataSource instance = null;
    private AppRemoteDataSource(){
        this.networkCallBack=networkCallBack;
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        network=retrofit.create(Network.class);
    }
    public static AppRemoteDataSource getInstance() {
        if(instance == null){
            instance=new AppRemoteDataSource();
        }
        return instance;
    }
    public void setNetworkCallBack(NetworkCallBack networkCallBack)
    {
        this.networkCallBack=networkCallBack;
    }
    public Observable<FoodCategoryResponse> getMealCategories()
    {
        Log.i("TAG", "getMealCategories: dddddddddddddddddddddddd");
        Observable<FoodCategoryResponse> call= network.getCategories();
        /*call.enqueue(new Callback<FoodCategoryResponse>() {
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
        });*/
        return call.subscribeOn(Schedulers.io());
    }
    public Observable<FoodCountryResponse> getCountries()
    {
        Observable<FoodCountryResponse> call= network.getCountries();
        /*call.enqueue(new Callback<FoodCountryResponse>() {
            @Override
            public void onResponse(Call<FoodCountryResponse> call, Response<FoodCountryResponse> response) {

                networkCallBack.onGetCountriesSuccessful(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<FoodCountryResponse> call, Throwable t) {
                networkCallBack.onGetCountriesFailure(t);
            }
        });*/
        return call.subscribeOn(Schedulers.io());
    }
    public Observable<MealsResponse> getRandomMeal()
    {
        Observable<MealsResponse> call=network.getMealOfTheDay();

        /*call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.d("TAG", "onResponse:ffffffffffffffff "+response.body());
                networkCallBack.onGetRandomMealSuccessful(response.body().getMealList().get(0));
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallBack.onGetRandomMealFailure(t);
            }
        });*/
        return call.subscribeOn(Schedulers.io());

    }
    public Observable<MealsResponse> getMeals(String mealName)
    {
        Observable<MealsResponse> call = network.getMeals(mealName);
        /*call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i("TAG", "onResponse: ddddddd" + response.body().getMealList().size() );
                List<Meal> mealList=response.body().getMealList();
                for(Meal meal:mealList)
                {
                    meal.setIngredientsList();
                }
                networkCallBack.onGetMealSuccessful(mealList);
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallBack.onGetMealFailure(t);
            }
        });*/
        return call.subscribeOn(Schedulers.io()).doOnNext(mealsResponse -> mealsResponse.getMealList().get(0).setIngredientsList());
    }
    public Observable<MealsResponse> getMealsByCategory(String category)
    {
        Observable<MealsResponse> call = network.getMealsByCategory(category);
//        call.enqueue(new Callback<MealsResponse>() {
//            @Override
//            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
//                Log.i("TAG", "onResponse: get meals by category "+ response.body().getMealList().size());
//                networkCallBack.onGetMealsByCategorySuccessful(response.body().getMealList());
//            }
//
//            @Override
//            public void onFailure(Call<MealsResponse> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        return call.subscribeOn(Schedulers.io());
    }
    public Observable<MealsResponse> getMealsByCountry(String country)
    {
        Observable<MealsResponse> observable=network.getMealsByCountry(country);
        return observable.subscribeOn(Schedulers.io());
    }
    public void getAllMeals()
    {
         /*we need to get all meals
         * all we can get now is meals crossponding to countries and categories
         * we can get all countries and categories
         * then we can make multiple api calls for each country then one for each category the concatenate them
         * */
    }
    public void getMealsListWithDetails()
    {

    }
}
