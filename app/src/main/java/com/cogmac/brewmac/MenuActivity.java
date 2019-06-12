package com.cogmac.brewmac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.cogmac.brewmac.adapter.DrinksAdapter;
import com.cogmac.brewmac.adapter.TempAdapter;
import com.cogmac.brewmac.model.DrinkItem;
import com.cogmac.brewmac.model.TempModel;
import com.cogmac.brewmac.utils.VerticalViewpager;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    public static VerticalViewpager verticalViewpager;
    public static final int LOOPS = 4;
    public static final int COUNT = 3;
    public static final int FIRST_PAGE = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Finding Vertical View Pager
        verticalViewpager = findViewById(R.id.vertical_viewpager);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int pageMargin = ((displayMetrics.heightPixels / 3) * 2);
        verticalViewpager.setPageMargin(-pageMargin);
        verticalViewpager.setAdapter(new TempAdapter(MenuActivity.this, getSupportFragmentManager()));

        ArrayList<DrinkItem> mDrinksItemList = new ArrayList<>();
        DrinkItem drink;
        for (int i = 0; i < 20; i++){
            drink = new DrinkItem("Drink" + i);
            mDrinksItemList.add(drink);
        }

        RecyclerView mDrinksRecyclerView = findViewById(R.id.recyclerView_drinks);
        mDrinksRecyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mDrinksRecyclerView.setAdapter(new DrinksAdapter(mDrinksItemList));

    }
}
