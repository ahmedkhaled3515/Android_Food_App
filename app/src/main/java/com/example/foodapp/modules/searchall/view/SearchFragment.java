package com.example.foodapp.modules.searchall.view;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.foodapp.R;
import com.example.foodapp.model.SearchResult;
import com.example.foodapp.modules.searchall.presenter.SearchPresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class SearchFragment extends Fragment implements SearchInterface,OnSearchClickListener{




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    View view;
    SearchView searchView;
    RecyclerView searchRecycler;
    SearchRecyclerAdapter adapter;
    SearchPresenter searchPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_search, container, false);
        searchView=view.findViewById(R.id.searchView3);
        searchRecycler=view.findViewById(R.id.searchRecyclerView);
        searchPresenter=new SearchPresenter(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        searchRecycler.setLayoutManager(layoutManager);
        search();
        return view;
    }
    public void search()
    {
        Observable<String> queryObservable= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        emitter.onNext(newText);
                        return false;
                    }
                });
            }
        }).debounce(1, TimeUnit.SECONDS);
        queryObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                searchPresenter.getSearch(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void showResult(List<SearchResult> resultsList) {
        adapter=new SearchRecyclerAdapter(this.getContext(),this,resultsList);
        searchRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchClick(SearchResult searchResult) {
        Bundle bundle=new Bundle();
        if(searchResult.getType().equals("country"))
        {
            bundle.putString("type","country");
            bundle.putString("country", searchResult.getResult());
            Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_categoryMealsFragment,bundle);

        }
        else if(searchResult.getType().equals("category"))
        {
            bundle.putString("type","category");
            bundle.putString("category", searchResult.getResult());
            Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_categoryMealsFragment,bundle);

        }
        else if (searchResult.getType().equals("ingredient")) {
            bundle.putString("type","ingredient");
            bundle.putString("ingredient", searchResult.getResult());
            Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_categoryMealsFragment,bundle);
        }
        else if (searchResult.getType().equals("meal"))
        {
            bundle.putString("meal", searchResult.getResult());
            Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_mealFragment,bundle);
        }

    }
}