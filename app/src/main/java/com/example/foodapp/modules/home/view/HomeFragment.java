package com.example.foodapp.modules.home.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.FoodCategory;
import com.example.foodapp.model.FoodCountryResponse;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.home.presenter.HomePresenter;
import com.example.foodapp.network.AppRemoteDataSource;
import com.example.foodapp.network.Network;
import com.example.foodapp.network.NetworkCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeInterface,CategoryClickListener  {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    Bundle bundle;
    View view;
    RecyclerView categoryRecycler;
    RecyclerView countryRecycler;
    CategoriesRecyclerAdapter categoryAdapter;
    CountriesRecyclerAdapter countriesAdapter;
    ImageView dailyImg;
    TextView tvDaily;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle=new Bundle();
        bundle.putString("name","ahmed");
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecycler=view.findViewById(R.id.categoryRecycler);
        countryRecycler=view.findViewById(R.id.countryRecycler);
        tvDaily=view.findViewById(R.id.tvDaily);
        dailyImg=view.findViewById(R.id.dailyImg);
        LinearLayoutManager categoryLayoutManager=new LinearLayoutManager(this.getContext());
        categoryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecycler.setLayoutManager(categoryLayoutManager);
        LinearLayoutManager countriesLayoutManger=new LinearLayoutManager(this.getContext());
        countriesLayoutManger.setOrientation(RecyclerView.HORIZONTAL);
        countryRecycler.setLayoutManager(countriesLayoutManger);
        HomePresenter homePresenter=new HomePresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showCategories(List<FoodCategory> categoryList) {
        categoryAdapter = new CategoriesRecyclerAdapter(this.getContext(),this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    @Override
    public void showCategoriesError(Throwable t) {
        Toast.makeText(getContext(),"SomeThing is Wrong with the network",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showCountries(List<FoodCountryResponse.FoodCountry> foodCountryList) {
        countriesAdapter=new CountriesRecyclerAdapter(foodCountryList);
        countryRecycler.setAdapter(countriesAdapter);
    }

    @Override
    public void showCountriesError(Throwable throwable) {
        Toast.makeText(getContext(),"SomeThing is Wrong with the network",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRandomMeal(Meal meal) {
        Glide.with(this.getContext())
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(dailyImg);
        Log.i("TAG", "showRandomMeal: ddddddddddddddddddd"+ meal.getStrMeal() + " " + meal.getStrMealThumb());
        tvDaily.setText(meal.getStrMeal());
    }

    @Override
    public void showRandomMealError(Throwable throwable) {
        Toast.makeText(getContext(),"SomeThing is Wrong with the network",Toast.LENGTH_SHORT).show();    }

    @Override
    public void showMeals(List<Meal> mealList) {

    }

    @Override
    public void showMealsError(Throwable throwable) {

    }

    @Override
    public void onCardClickListener(String category) {
        bundle.putString("category",category);
        NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_categoryMealsFragment,bundle);    }
}