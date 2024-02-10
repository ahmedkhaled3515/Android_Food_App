package com.example.foodapp.modules.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.FoodCategory;

import java.util.List;
import java.util.zip.Inflater;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHandler> {
    List<FoodCategory> categoryList;
    public CategoriesRecyclerAdapter(List<FoodCategory> categoryList)
    {
        this.categoryList=categoryList;
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
        holder.tvCategory.setText(categoryList.get(position).getTitle());
        holder.categoryImg.setImageResource(categoryList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView tvCategory;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            categoryImg= itemView.findViewById(R.id.categoryImg);
            tvCategory=itemView.findViewById(R.id.tvCategory);
        }
    }
}
