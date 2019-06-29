package com.cogmac.brewmac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cogmac.brewmac.R;
import com.cogmac.brewmac.model.DrinkItem;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<DrinkItem> mCartItemsList;
    private Context mContext;

    public CartAdapter(Context context, ArrayList<DrinkItem> mCartItemsList) {
        this.mCartItemsList = mCartItemsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrinkItem item = mCartItemsList.get(position);
        holder.tvDrinkName.setText(item.getmDrinkTitle());
        String index = String.valueOf(position + 1);
        String serial = mContext.getResources().getString(R.string.serialText, index);
        holder.tvCartSerial.setText(serial);
        if (position % 2 == 0){
            holder.tvCartSerial.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.tvDrinkName.setTextColor(mContext.getResources().getColor(R.color.colorYellow));
        }
        else{
            holder.tvCartSerial.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.tvDrinkName.setTextColor(mContext.getResources().getColor(R.color.colorViolet));
        }
    }

    @Override
    public int getItemCount(){
        return mCartItemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDrinkName, tvCartSerial;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDrinkName = itemView.findViewById(R.id.tv_cart_drinkName);
            tvCartSerial = itemView.findViewById(R.id.tv_cart_serial);
        }
    }
    public void setItems(ArrayList<DrinkItem> cartItems) {
        this.mCartItemsList = cartItems;
    }
}
