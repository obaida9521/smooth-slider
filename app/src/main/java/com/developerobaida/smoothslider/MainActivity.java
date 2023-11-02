package com.developerobaida.smoothslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);

        /*-----=========--------use this if you want to show image from drawable--------==========------

        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(""+R.drawable.ic_launcher_background));
        slideItems.add(new SlideItem(""+R.drawable.ic_launcher_background));
        slideItems.add(new SlideItem(""+R.drawable.ic_launcher_background));
         */

        //-----=========--------use this if you want to show image from url--------==========------
        String url = "https://putYourLinkHere.com";
        List<SlideItem> slideItems = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {


            for (int i = 0; i<=response.length(); i++){

                try {

                    JSONObject jsonObject = response.getJSONObject(i);
                    String getImages = jsonObject.getString("put here your value of json");

                    slideItems.add(new SlideItem(getImages));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, error -> {
            //----======handle errors=======-------
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);





        //-=-=-=-=-=-=-=-=-=-=-----------------=-=-=-=-=-=-=--=-=-=-=
        //-=-=-=-=-=-=-=-=-=-=-----------------=-=-=-=-=-=-=--=-=-=-=
        viewPager2.setAdapter(new SliderAdapter(slideItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        //-=-=-=-=-=-=-=-=-=-=-----------------=-=-=-=-=-=-=--=-=-=-=
        //-=-=-=-=-=-=-=-=-=-=-----------------=-=-=-=-=-=-=--=-=-=-=
    }
}