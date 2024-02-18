package com.example.foodapp.modules.showmeal.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;

import java.util.List;

public class IngerdientsRecyclerAdapter extends RecyclerView.Adapter<IngerdientsRecyclerAdapter.ViewHolder> {
    List<String> ingredientsList;
    Context context;
    public IngerdientsRecyclerAdapter(Context context, List<String> ingredientsList)
    {
        this.ingredientsList=ingredientsList;
        this.context=context;
    }
    @NonNull
    @Override
    public IngerdientsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.ingredients_card,parent,false);
        IngerdientsRecyclerAdapter.ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngerdientsRecyclerAdapter.ViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: "+ingredientsList.get(position));
        holder.ingredientName.setText(ingredientsList.get(position));
        holder.ingredientImg.setImageResource(R.drawable.pizza_img);
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/"+ingredientsList.get(position)+"-Small.png")
                .circleCrop()
                .into(holder.ingredientImg);
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ingredientImg;
        TextView ingredientName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImg=itemView.findViewById(R.id.ingredientImg);
            ingredientName=itemView.findViewById(R.id.tvIngredients);
        }
    }
}
