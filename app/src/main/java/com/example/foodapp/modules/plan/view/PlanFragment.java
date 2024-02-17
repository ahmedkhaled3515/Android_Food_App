package com.example.foodapp.modules.plan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.model.Plan;
import com.example.foodapp.modules.plan.presenter.PlanPresenter;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PlanFragment extends Fragment implements PlanInterface ,OnPlanClickListener{

    View view;
    RecyclerView recyclerView;
    PlanRecyclerAdapter planAdapter;
    PlanPresenter planPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        recyclerView=view.findViewById(R.id.planRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        planPresenter=new PlanPresenter(this.getContext(),this);
        return view;
    }

    @Override
    public void showPlans(Flowable<List<Plan>> plan) {
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        plan.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(planList -> {
                    Log.d("TAG", "showPlans: " +planList);
                    planList.removeIf(plan1 -> !plan1.getEmail().equals(email));
                    planAdapter=new PlanRecyclerAdapter(this.getContext(),this,planList);
                    recyclerView.setAdapter(planAdapter);
                });
    }

    @Override
    public void onRemoveClick(Plan plan) {
        planPresenter.removePlan(plan);
    }

    @Override
    public void onViewClick(String mealName) {
        Bundle bundle=new Bundle();
        bundle.putString("type","meal");
        bundle.putString("meal",mealName);
        Navigation.findNavController(view).navigate(R.id.action_planFragment_to_mealFragment,bundle);
    }
}