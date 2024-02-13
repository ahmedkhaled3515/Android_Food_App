package com.example.foodapp.modules.showmeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodapp.R;
import com.example.foodapp.model.Meal;
import com.example.foodapp.modules.showmeal.presenter.MealPresenter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_meal, container, false);
        // Inflate the layout for this fragment
        MealPresenter presenter=new MealPresenter(this);
        mealImg = view.findViewById(R.id.mealImgMealFragment);
        tvMealName = view.findViewById(R.id.mealNameMealFragment);
        tvMealCategory = view.findViewById(R.id.mealCategoryMealFragment);
        tvMealCountry=view.findViewById(R.id.mealAreaMealFragment);
        tvMealDescription=view.findViewById(R.id.mealDescriptionMealFragment);
        webView=view.findViewById(R.id.webView);

        return view;
    }

    @Override
    public void showMealDetails(Meal meal) {
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .transform(new RoundedCorners(50))
                .into(mealImg);
        tvMealName.setText(meal.getStrMeal());
        tvMealCategory.setText(meal.getStrCategory());
        tvMealCountry.setText(meal.getStrArea());
        tvMealDescription.setText(meal.getStrInstructions());
        setMealVideo(meal.getStrYoutube());
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
        String videoUrl = convertToEmbeddedUrl("https://www.youtube.com/watch?v=1IszT_guI08"); // Replace VIDEO_ID with the actual video ID or embed URL
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