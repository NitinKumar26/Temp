package com.cogmac.brewmac.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.cogmac.brewmac.R;
import com.cogmac.brewmac.adapter.CarouselPagerAdapter;

public class MainActivity extends AppCompatActivity {
    public static ViewPager main_viewpager;
    public static final int FIRST_PAGE = 0;

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

        //int pagerPadding = 16;
        //main_viewpager.setClipToPadding(true);
        //main_viewpager.setPadding(0, 0, pageMargin/3, 0);

        Log.e("-pageMargin", String.valueOf(-pageMargin));

        final CarouselPagerAdapter pagerAdapter = new CarouselPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        main_viewpager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        main_viewpager.addOnPageChangeListener(pagerAdapter);
        //main_viewpager.setCurrentItem(0);
        main_viewpager.setOffscreenPageLimit(pagerAdapter.getCount());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
