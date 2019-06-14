package com.cogmac.brewmac.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.cogmac.brewmac.R;
import com.cogmac.brewmac.adapter.DrinksAdapter;
import com.cogmac.brewmac.model.DrinkItem;
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
        setContentView(R.layout.new_activity_menu);

        final ArrayList<DrinkItem> mDrinksItemList = new ArrayList<>();
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
