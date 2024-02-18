package com.example.foodapp.modules.categorymeals.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.categorymeals.presenter.MealsListPresenter;

import java.util.List;


public class MealsListFragment extends Fragment implements MealsListInterface, MealsClickListener{


    Bundle bundle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    RecyclerView recyclerView;
    MealRecyclerAdapter mealRecyclerAdapter;
    TextView tvEmpty;
    MealsListPresenter presenter;
    TextView tvTotal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle= new Bundle();
        String category="";
        String country="";
        String ingredient="";
        Bundle args = getArguments();
        presenter=new MealsListPresenter(this.getContext(),this);
        if (args != null) {

            category = args.getString("category");
            country = args.getString("country");
            ingredient=args.getString("ingredient");
            String type= args.getString("type");
            if(type.equals("country"))
            {
                presenter.getMealsByCountry(country);
            }
            else if(type.equals("category"))
            {
                presenter.getMealsByCategory(category);
            }
            else if(type.equals("ingredient"))
            {
                presenter.getMealsByIngredients(ingredient);
            }
            Log.d("TAG", "onCreateView: hello from meals category "+ category);
        }
        View view =inflater.inflate(R.layout.fragment_category_meals, container, false);
        tvTotal=view.findViewById(R.id.tvListTotal);
        tvEmpty=view.findViewById(R.id.tvEmptyView);
        recyclerView = view.findViewById(R.id.mealsRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Log.i("TAG", "onCreateView: categoriesMeals fragment " );
        return view;
    }

    @Override
    public void showMeals(List<Meal> mealList) {
        if(mealList == null)
        {
           tvEmpty.setVisibility(View.VISIBLE);
        }
        else {
            tvTotal.setText("Total "+mealList.size()+" items");
            tvEmpty.setVisibility(View.GONE);
            Log.i("TAG", "showMeals: ddddddddddddddddd");
            mealRecyclerAdapter = new MealRecyclerAdapter(this.getContext(), this, mealList);
            recyclerView.setAdapter(mealRecyclerAdapter);
        }
    }

    @Override
    public void showMealsError(Throwable t) {

    }

    @Override
    public void onViewClickListener(Meal clickedMeal) {
        bundle.putString("meal",clickedMeal.getStrMeal());
        NavHostFragment.findNavController(MealsListFragment.this).navigate(R.id.action_categoryMealsFragment_to_mealFragment,bundle);
    }

    @Override
    public void onFavoriteClickListener(Meal clickedMeal) {
        presenter.addMealToFav(clickedMeal);
    }
}