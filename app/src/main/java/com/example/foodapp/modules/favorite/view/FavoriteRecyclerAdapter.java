package com.example.foodapp.modules.favorite.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class FavoriteRecyclerAdapter extends RecyclerView.Adapter<FavoriteRecyclerAdapter.ViewHolder> {
    Context context;
    List<Meal> mealList;
    OnFavoriteClickListener onFavoriteClickListener;
    public FavoriteRecyclerAdapter(Context context,OnFavoriteClickListener onFavoriteClickListener,List<Meal> mealList)
    {
        this.onFavoriteClickListener=onFavoriteClickListener;
        this.context=context;
        this.mealList=mealList;
    }
    @NonNull
    @Override
    public FavoriteRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.favorite_card_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvMealName.setText(mealList.get(position).getStrMeal());
        Glide.with(context)
                .load(mealList.get(position).getStrMealThumb())
                .into(holder.imageView);
        if(!isNetworkAvailable(context))
        {
            holder.btnView.setVisibility(View.GONE);
        }
        holder.btnView.setOnClickListener(v -> {
            onFavoriteClickListener.onViewClick(mealList.get(position));
        });
        holder.btnRemove.setOnClickListener(v -> {
            onFavoriteClickListener.onRemoveClick(mealList.get(position));
        });
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvMealName;
        Button btnView;
        Button btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.favImg);
            tvMealName=itemView.findViewById(R.id.tvFavoriteTitle);
            btnRemove=itemView.findViewById(R.id.btnRemove);
            btnView=itemView.findViewById(R.id.btnFavView);
        }
    }
}
