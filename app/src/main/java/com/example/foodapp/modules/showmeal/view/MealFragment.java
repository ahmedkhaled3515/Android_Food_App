package com.example.foodapp.modules.showmeal.view;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodapp.R;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.Plan;
import com.example.foodapp.modules.showmeal.presenter.MealPresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MealFragment extends Fragment implements MealInterface {

    ImageView mealImg;
    TextView tvMealName;
    TextView tvMealCountry;
    TextView tvMealCategory;
    TextView tvMealDescription;
    VideoView mealVideo;
    WebView webView;
    View view;
    RecyclerView recyclerView;
    IngerdientsRecyclerAdapter ingredientsAdapter;
    Button btnAddToFavorite;
    Button btnAddToPlan;
    MealPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_meal, container, false);

        // Inflate the layout for this fragment
        Bundle arg=getArguments();
        if(arg !=null) {
            String mealName=arg.getString("meal");
            presenter = new MealPresenter(this.getContext(),this,mealName);
        }
        btnAddToFavorite=view.findViewById(R.id.btnAddFav);
        btnAddToPlan=view.findViewById(R.id.btnAddToPlan);
        mealImg = view.findViewById(R.id.mealImgMealFragment);
        tvMealName = view.findViewById(R.id.mealNameMealFragment);
        tvMealCategory = view.findViewById(R.id.mealCategoryMealFragment);
        tvMealCountry=view.findViewById(R.id.mealAreaMealFragment);
        tvMealDescription=view.findViewById(R.id.mealDescriptionMealFragment);
        webView=view.findViewById(R.id.webView);
        recyclerView = view.findViewById(R.id.ingrediantRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }
    String email = null;
    @Override
    public void showMealDetails(Meal meal) {

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        }
        else {
            btnAddToPlan.setVisibility(View.GONE);
            btnAddToFavorite.setVisibility(View.GONE);
        }

        Glide.with(this)
                .load(meal.getStrMealThumb())
                .transform(new RoundedCorners(50))
                .into(mealImg);
        tvMealName.setText(meal.getStrMeal());
        tvMealCategory.setText(meal.getStrCategory());
        tvMealCountry.setText(meal.getStrArea());
        tvMealDescription.setText(meal.getStrInstructions());
        setMealVideo(meal.getStrYoutube());
        ingredientsAdapter=new IngerdientsRecyclerAdapter(this.getContext(),meal.getIngredientsList());
        recyclerView.setAdapter(ingredientsAdapter);
        btnAddToPlan.setOnClickListener(v -> {
//            showPlanPopUp(meal)
            Calendar calendar= Calendar.getInstance();
            DatePickerDialog dialog=new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Log.d("TAG", "onDateSet: " + month);
//                    etDate.setText(String.valueOf(year)+"/"+String.valueOf((Calendar.MONTH+month)-1)+"/"+String.valueOf(dayOfMonth));
                    date=new Date(year-1900,(Calendar.MONTH+month)-2,dayOfMonth);
                    Log.d("TAG", "showPlanPopUp: "+ date);

                    Plan plan = new Plan(meal.getIdMeal(), email, meal.getStrMeal(), meal.getStrMealThumb(), date);
                    presenter.addToPlan(plan);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();

        });
        btnAddToFavorite.setOnClickListener(v -> {
            Log.d("TAG", "showMealDetails: "+meal);
            meal.setEmail(email);
            presenter.addToFav(meal);
        });
    }
    Date date = null;
    private void showPlanPopUp(Meal meal)
    {
        View popupView = getLayoutInflater().inflate(R.layout.plan_popup,null);
        Button btnPickDate=popupView.findViewById(R.id.btnPickDate);
        Button btnConfirm=popupView.findViewById(R.id.btnDateConfirm);
        EditText etDate=popupView.findViewById(R.id.etDate);

        btnPickDate.setOnClickListener(v -> {
            Calendar calendar= Calendar.getInstance();
            DatePickerDialog dialog=new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Log.d("TAG", "onDateSet: " + month);
                    etDate.setText(String.valueOf(year)+"/"+String.valueOf((Calendar.MONTH+month)-1)+"/"+String.valueOf(dayOfMonth));
                    date=new Date(year-1900,(Calendar.MONTH+month)-2,dayOfMonth);
                    Log.d("TAG", "showPlanPopUp: "+ date);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });
        btnConfirm.setOnClickListener(v -> {
            if(date !=null) {
                Log.d("TAG", "showPlanPopUp2 : " + date);
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                Plan plan = new Plan(meal.getIdMeal(), email, meal.getStrMeal(), meal.getStrMealThumb(), date);
                presenter.addToPlan(plan);
            }
            else {
                Toast.makeText(this.getContext(),"Date can't be empty",Toast.LENGTH_SHORT).show();
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int popupWidth = (int) (screenWidth * 0.85); // 75% of screen width
        int popupHeight = (int) (screenHeight * 0.50); // 75% of screen height

        // Create PopupWindow with calculated dimensions
        PopupWindow popupWindow = new PopupWindow(popupView, popupWidth, popupHeight, true);
        popupWindow.showAtLocation(recyclerView, Gravity.CENTER, 0, -200);
    }
    public static String convertToEmbeddedUrl(String youtubeUrl) {
        String videoId = extractVideoId(youtubeUrl);
        return "https://www.youtube.com/embed/" + videoId;
    }

    private static String extractVideoId(String youtubeUrl) {
        String videoId = null;
        String regex = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2Fvideos%2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(youtubeUrl);
        if (matcher.find()) {
            videoId = matcher.group();
        }

        return videoId;
    }
    public void setMealVideo(String url)
    {
        Log.d("TAG", "setMealVideo: width " +webView.getX());
        String videoUrl = convertToEmbeddedUrl(url); // Replace VIDEO_ID with the actual video ID or embed URL
        String video="<iframe width='400' height=\"200\" src= '"+videoUrl+"' title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript (required for video playback)
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                adjustIframeWidth(view);
            }
        });
        webView.loadData(video, " text/html", "utf-8"); // Load the HTML content into the WebView
//        webView.loadUrl(url);

    }
    private void adjustIframeWidth(WebView webView) {
        webView.evaluateJavascript("javascript:(function() { " +
                "var iframes = document.getElementsByTagName('iframe');" +
                "for (var i = 0; i < iframes.length; i++) {" +
                "    var iframe = iframes[i];" +
                "    iframe.style.width = '100%';" +
                "}})();", null);
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void showMealDetailsError(Throwable t) {

    }

}