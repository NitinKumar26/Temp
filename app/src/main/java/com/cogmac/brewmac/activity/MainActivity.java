package com.cogmac.brewmac.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.cogmac.brewmac.R;
import com.cogmac.brewmac.adapter.CarouselPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public static ViewPager main_viewpager;
    public static final int FIRST_PAGE = 1;

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

        final CarouselPagerAdapter pagerAdapter = new CarouselPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this);
        main_viewpager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        main_viewpager.addOnPageChangeListener(pagerAdapter);
        main_viewpager.setOffscreenPageLimit(6);
    }
}
