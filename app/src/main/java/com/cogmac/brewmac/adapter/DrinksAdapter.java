package com.cogmac.brewmac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.model.DrinkItem;

import java.util.ArrayList;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    private final ArrayList<DrinkItem> mDrinksList;

    public DrinksAdapter(ArrayList<DrinkItem> mDrinksList) {
        this.mDrinksList = mDrinksList;
    }

    @NonNull
    @Override
    public DrinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.drink_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DrinkItem drink = mDrinksList.get(i);
        viewHolder.tvDrinkTitle.setText(drink.getmDrinkTitle());
        viewHolder.iconImage.setImageResource(drink.getmDrinkIcon());
    }

    @Override
    public int getItemCount() {
        return mDrinksList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvDrinkTitle;
        final RelativeLayout relativeLayout;
        ImageView iconImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDrinkTitle = itemView.findViewById(R.id.title);
            relativeLayout = itemView.findViewById(R.id.drink_relative);
            iconImage = itemView.findViewById(R.id.icon_drink);
        }
    }
}
