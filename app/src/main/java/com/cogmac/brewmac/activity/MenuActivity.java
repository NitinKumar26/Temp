package com.cogmac.brewmac.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.adapter.CartAdapter;
import com.cogmac.brewmac.adapter.DrinksAdapter;
import com.cogmac.brewmac.helper.HelperMethods;
import com.cogmac.brewmac.model.CartItem;
import com.cogmac.brewmac.model.DrinkItem;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    public static final int LOOPS = 4;
    public static final int COUNT = 3;
    public static final int FIRST_PAGE = 3;
    private ArrayList<CartItem> cartItems;
    private RecyclerView mCartRecyclerView, mDrinksRecyclerView;
    private CartAdapter cartAdapter;
    private Button mPourButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mCartRecyclerView = findViewById(R.id.recyclerView_cart);
        mDrinksRecyclerView = findViewById(R.id.recyclerView_drinks);
        mPourButton = findViewById(R.id.btn_pour);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, RecyclerView.VERTICAL, true ));
        mDrinksRecyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, LinearLayoutManager.HORIZONTAL, false));

        final ArrayList<DrinkItem> mDrinksItemList = new ArrayList<>();
        mDrinksItemList.add(new DrinkItem("Apple Margarita", R.drawable.ic_beer));
        mDrinksItemList.add(new DrinkItem("Aviation Cocktail", R.drawable.ic_can));
        mDrinksItemList.add(new DrinkItem("Bahama Mama", R.drawable.ic_champagne_glass));
        mDrinksItemList.add(new DrinkItem("Bay Breeze", R.drawable.ic_glass));
        mDrinksItemList.add(new DrinkItem("Bellini", R.drawable.ic_glass_champagne));
        mDrinksItemList.add(new DrinkItem("Bee's Knees", R.drawable.ic_glass_new));
        mDrinksItemList.add(new DrinkItem("Black & Blue", R.drawable.ic_glass_new2));

        mDrinksRecyclerView.setAdapter(new DrinksAdapter(mDrinksItemList));
        mDrinksRecyclerView.addOnItemTouchListener(new HelperMethods.RecyclerTouchListener(MenuActivity.this, new HelperMethods.ClickListener() {
            @Override
            public void onClick(int position) {
                DrinkItem drinkItem = mDrinksItemList.get(position);
                String drinkName = drinkItem.getmDrinkTitle();
                if (cartItems == null){
                    cartItems = new ArrayList<>();
                    cartItems.add(new CartItem(drinkName));
                    cartAdapter = new CartAdapter(MenuActivity.this, cartItems);
                    mCartRecyclerView.setAdapter(cartAdapter);
                }else{
                    if (cartItems.size() < 10){
                        cartItems.add(new CartItem(drinkName));
                        cartAdapter.setItems(cartItems);
                        cartAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(MenuActivity.this, "You have reached the maximum limit of drinks", Toast.LENGTH_LONG).show();
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
