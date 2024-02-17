package com.example.foodapp.modules.plan.view;

import android.content.Context;
import android.util.Log;
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
import com.example.foodapp.model.Plan;

import java.util.List;

public class PlanRecyclerAdapter extends RecyclerView.Adapter<PlanRecyclerAdapter.ViewHolder> {
    Context context;
    List<Plan> planList;
    OnPlanClickListener onPlanClickListener;
    public PlanRecyclerAdapter(Context context,OnPlanClickListener onPlanClickListener, List<Plan> planList)
    {
        this.onPlanClickListener=onPlanClickListener;
        this.context=context;
        this.planList=planList;
        Log.d("TAG", "PlanRecyclerAdapter: "+planList);
    }
    @NonNull
    @Override
    public PlanRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plan_card_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanRecyclerAdapter.ViewHolder holder, int position) {
        holder.planTitle.setText(planList.get(position).getStrMeal());
        Glide.with(context)
                .load(planList.get(position).getStrMealThumb())
                .into(holder.planImg);
        holder.btnRemove.setOnClickListener(v -> {
            onPlanClickListener.onRemoveClick(planList.get(position));
        });
        holder.dateText.setText(String.valueOf(planList.get(position).getDate()));
        holder.btnView.setOnClickListener(v -> {
            onPlanClickListener.onViewClick(planList.get(position).getStrMeal());
        });
    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView planImg;
        TextView planTitle;
        Button btnRemove;
        TextView dateText;
        Button btnView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            planImg=itemView.findViewById(R.id.planImage);
            planTitle=itemView.findViewById(R.id.tvPlanMealTitle);
            btnRemove=itemView.findViewById(R.id.btnRemovePlan);
            dateText=itemView.findViewById(R.id.tvDate);
            btnView=itemView.findViewById(R.id.btnViewPlan);
        }
    }
}
