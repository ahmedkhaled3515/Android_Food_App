package com.example.foodapp.modules.categorymeals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.Meal;

import java.util.List;

public class MealRecyclerAdapter extends RecyclerView.Adapter<MealRecyclerAdapter.ViewHolder> {
    List<Meal> mealList;
    Context context;
    MealsClickListener clickListener;
    public MealRecyclerAdapter(Context context,MealsClickListener clickListener,List<Meal> mealList)
    {
        this.clickListener=clickListener;
        this.context=context;
        this.mealList=mealList;
    }
    @NonNull
    @Override
    public MealRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.meal_card_layout,parent,false  );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealRecyclerAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(mealList.get(position).getStrMealThumb())
                .into(holder.imgView);
        holder.tvMeal.setText(mealList.get(position).getStrMeal());
        holder.btnView.setOnClickListener(v -> {
            clickListener.onViewClickListener(mealList.get(position));
        });
        holder.btnFavourite.setOnClickListener(v -> {
            clickListener.onFavoriteClickListener(mealList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView tvMeal;
        Button btnFavourite;
        Button btnView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnView=itemView.findViewById(R.id.btnView);
            imgView=itemView.findViewById(R.id.mealImage);
            tvMeal=itemView.findViewById(R.id.tvMealName);
            btnFavourite=itemView.findViewById(R.id.btnFavorite);
        }
    }
}
