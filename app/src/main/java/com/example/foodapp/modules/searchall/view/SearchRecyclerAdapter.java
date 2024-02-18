package com.example.foodapp.modules.searchall.view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.SearchResult;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {
    Context context;
    List<SearchResult> searchList;
    OnSearchClickListener onSearchClickListener;
    public SearchRecyclerAdapter(Context context,OnSearchClickListener onSearchClickListener,List<SearchResult> stringList)
    {
        this.onSearchClickListener=onSearchClickListener;
        this.context=context;
        this.searchList=stringList;
    }
    @NonNull
    @Override
    public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.search_card,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvResult.setText(searchList.get(position).getResult());
        holder.itemView.setOnClickListener(v -> {
            onSearchClickListener.onSearchClick(searchList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvResult;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResult=itemView.findViewById(R.id.tvResult);
        }
    }
}
