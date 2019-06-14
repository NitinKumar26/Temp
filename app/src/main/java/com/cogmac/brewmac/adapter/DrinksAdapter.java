package com.cogmac.brewmac.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.model.DrinkItem;

import java.util.ArrayList;
import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    private ArrayList<DrinkItem> mDrinksList;

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

    }

    @Override
    public int getItemCount() {
        return mDrinksList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDrinkTitle;
        RelativeLayout relativeLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDrinkTitle = itemView.findViewById(R.id.title);
            relativeLayout = itemView.findViewById(R.id.drink_relative);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    relativeLayout.setBackground(v.getContext().getResources().getDrawable(R.drawable.drink_item_selection_background));

                }
            });
        }
    }
}
