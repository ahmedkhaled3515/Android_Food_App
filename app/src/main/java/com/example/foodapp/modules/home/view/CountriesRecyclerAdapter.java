package com.example.foodapp.modules.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.FoodCountryResponse;

import java.util.List;

public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.ViewHolder> {
    List<FoodCountryResponse.FoodCountry> countries;
    CountryClickListener clickListener;
    public CountriesRecyclerAdapter(CountryClickListener clickListener,List<FoodCountryResponse.FoodCountry> countries)
    {
        this.countries=countries;
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    public CountriesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.countries_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvCountry.setText(countries.get(position).getStrArea());
        holder.itemView.setOnClickListener(v -> {
            clickListener.onCountryClickListener(countries.get(position).getStrArea());
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountry;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry=itemView.findViewById(R.id.tvCountry);
        }
    }
}
