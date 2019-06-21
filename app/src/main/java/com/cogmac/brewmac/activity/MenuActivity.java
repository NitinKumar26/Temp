package com.cogmac.brewmac.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.adapter.CartAdapter;
import com.cogmac.brewmac.adapter.DrinksAdapter;
import com.cogmac.brewmac.helper.HelperMethods;
import com.cogmac.brewmac.model.CartItem;
import com.cogmac.brewmac.model.DrinkItem;
import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    public static final int LOOPS = 4;
    public static final int COUNT = 1;
    public static final int FIRST_PAGE = 3;
    private ArrayList<DrinkItem> cartItems;
    private RecyclerView mCartRecyclerView;
    private CartAdapter cartAdapter;
    private DrinkItem drinkItem;
    private String drinkName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

        final ImageView glass = findViewById(R.id.glass);
        mCartRecyclerView = findViewById(R.id.recyclerView_cart);
        RecyclerView mDrinksRecyclerView = findViewById(R.id.recyclerView_drinks);
        final VectorChildFinder vector = new VectorChildFinder(MenuActivity.this, R.drawable.ic_glass, glass);

        Button mPourButton = findViewById(R.id.btn_pour);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, RecyclerView.VERTICAL, true ));
        mDrinksRecyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, LinearLayoutManager.HORIZONTAL, false));

        final ArrayList<DrinkItem> mDrinksItemList = new ArrayList<>();
        mDrinksItemList.add(new DrinkItem("Apple Margarita", R.drawable.ic_beer));
        mDrinksItemList.add(new DrinkItem("Aviation Cocktail", R.drawable.ic_can));
        mDrinksItemList.add(new DrinkItem("Beer", R.drawable.ic_beer_pack));
        mDrinksItemList.add(new DrinkItem("Wine", R.drawable.ic_wine));
        mDrinksItemList.add(new DrinkItem("Bahama Mama", R.drawable.ic_champagne_glass));
        mDrinksItemList.add(new DrinkItem("Bay Breeze", R.drawable.ic_bay_breeze));
        mDrinksItemList.add(new DrinkItem("Bellini", R.drawable.ic_glass_champagne));
        mDrinksItemList.add(new DrinkItem("Bee's Knees", R.drawable.ic_glass_new));
        mDrinksItemList.add(new DrinkItem("Black & Blue", R.drawable.ic_glass_new2));

        mDrinksRecyclerView.setAdapter(new DrinksAdapter(mDrinksItemList));
        mDrinksRecyclerView.addOnItemTouchListener(new HelperMethods.RecyclerTouchListener(MenuActivity.this, new HelperMethods.ClickListener() {
            @Override
            public void onClick(int position) {
                drinkItem = mDrinksItemList.get(position);
                drinkName = drinkItem.getmDrinkTitle();
                VectorDrawableCompat.VFullPath path;
                if (cartItems == null){
                    cartItems = new ArrayList<>();
                    cartItems.add(new DrinkItem(drinkName));
                    cartAdapter = new CartAdapter(MenuActivity.this, cartItems);
                    mCartRecyclerView.setAdapter(cartAdapter);
                    path = vector.findPathByName("path1");
                    path.setFillColor(MenuActivity.this.getResources().getColor(R.color.colorPath1));
                    glass.invalidate();
                }else{
                    if (cartItems.size() < 7) {
                            if (!cartItems.contains(drinkItem)){
                                cartItems.add(new DrinkItem(drinkName));
                                cartAdapter.setItems(cartItems);
                                cartAdapter.notifyDataSetChanged();
                                switch (cartItems.size()){
                                    case 2:
                                        path = vector.findPathByName("path2");
                                        path.setFillColor(MenuActivity.this.getResources().getColor(R.color.colorPath2));
                                        glass.invalidate();
                                        break;
                                    case 3:
                                        path = vector.findPathByName("path3");
                                        path.setFillColor(MenuActivity.this.getResources().getColor(R.color.colorPath3));
                                        glass.invalidate();
                                        break;
                                    case 4:
                                        path = vector.findPathByName("path4");
                                        path.setFillColor(MenuActivity.this.getResources().getColor(R.color.colorPath4));
                                        glass.invalidate();
                                        break;
                                }
                            }else{
                                Toast.makeText(MenuActivity.this, drinkName + " is already added", Toast.LENGTH_SHORT).show();
                            }
                    }else{
                        Toast.makeText(MenuActivity.this, "You have reached the maximum limit.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }));

        mPourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartItems == null || cartItems.isEmpty()){
                    Toast.makeText(MenuActivity.this, "Please add drinks", Toast.LENGTH_SHORT).show();
                }else{
                    //TODO:SHOW PROGRESS DIALOG WHILE THE DRINKS ARE POURING.
                }
            }
        });
    }
}
