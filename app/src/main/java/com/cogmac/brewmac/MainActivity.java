package com.cogmac.brewmac;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;

import com.cogmac.brewmac.adapter.CarouselPagerAdapter;
import com.cogmac.brewmac.utils.ClickableViewpager;

public class MainActivity extends AppCompatActivity {

    public static ViewPager main_viewpager;
    public static final int count = 4;
    public static final int LOOPS = 1;
    public static final int FIRST_PAGE = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_viewpager = findViewById(R.id.main_viewpager);


        //Set ViewPager Margin According to Device's Display
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 3) * 2);
        main_viewpager.setPageMargin(-pageMargin);


        CarouselPagerAdapter pagerAdapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        main_viewpager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        main_viewpager.addOnPageChangeListener(pagerAdapter);
        main_viewpager.setOffscreenPageLimit(3);

        /*
        main_viewpager.setOnItemClickListener(new ClickableViewpager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, MenuActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, CustomActivity.class ));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;
                }
            }
        });*/

    }
}