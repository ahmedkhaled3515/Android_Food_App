package com.example.foodapp.modules.categorymeals.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryMealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryMealsFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    RecyclerView recyclerView;
    MealRecyclerAdapter mealRecyclerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_category_meals, container, false);
        recyclerView = view.findViewById(R.id.mealsRecycler);
        
        return view;
    }
}