package com.cogmac.brewmac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import com.cogmac.brewmac.model.TempModel;
import com.cogmac.brewmac.utils.VerticalViewpager;
import java.util.ArrayList;

public class TempActivity extends AppCompatActivity {
    public static VerticalViewpager verticalViewpager;
    public static final int LOOPS = 4;
    public static final int COUNT = 3;
    public static final int FIRST_PAGE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        //Finding Vertical View Pager
        verticalViewpager = findViewById(R.id.vertical_viewpager);

        ArrayList<TempModel> tempModels = new ArrayList<>();
        tempModels.add(new TempModel(R.drawable.ic_menu));
        tempModels.add(new TempModel(R.drawable.ic_custom));
        tempModels.add(new TempModel(R.drawable.ic_settings));


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int pageMargin = ((displayMetrics.heightPixels / 3) * 2);
        verticalViewpager.setPageMargin(-pageMargin);

        //verticalViewpager.setAdapter(new TempAdapter(TempActivity.this, getSupportFragmentManager()));

    }
}
