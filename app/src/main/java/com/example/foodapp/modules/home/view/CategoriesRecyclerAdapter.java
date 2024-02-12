package com.example.foodapp.modules.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.FoodCategory;

import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHandler> {
    CategoryClickListener categoryClickListener;
    List<FoodCategory> categoryList;
    Context context;
    public CategoriesRecyclerAdapter(Context context,CategoryClickListener categoryClickListener, List<FoodCategory> categoryList)
    {
        this.categoryClickListener=categoryClickListener;
        this.categoryList=categoryList;
        this.context=context;
    }
    @NonNull
    @Override
    public CategoriesRecyclerAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_layout,parent,false);
        return new ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesRecyclerAdapter.ViewHandler holder, int position) {
        holder.tvCategory.setText(categoryList.get(position).getStrCategory());
        Glide.with(context)
                .load(categoryList.get(position).getStrCategoryThumb())
                .into(holder.categoryImg);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.onCardClickListener(categoryList.get(position).getStrCategory());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView tvCategory;
        ConstraintLayout constraintLayout;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            categoryImg= itemView.findViewById(R.id.categoryImg);
            tvCategory=itemView.findViewById(R.id.tvCategories);
            constraintLayout=itemView.findViewById(R.id.categoryLayout);
        }
    }
}
