package com.cogmac.brewmac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.activity.BeveragesActivity;
import com.cogmac.brewmac.activity.CustomActivity;
import com.cogmac.brewmac.activity.FavouritesActivity;
import com.cogmac.brewmac.activity.MenuActivity;
import com.cogmac.brewmac.activity.RecipeActivity;
import com.cogmac.brewmac.activity.SettingsActivity;
import com.cogmac.brewmac.utils.CarouselLinearLayout;
import com.cogmac.brewmac.utils.ListConfig;

public class CarouselItemFragment extends Fragment {
    private static final String POSITION = "position";
    private static final String SCALE = "scale";

    public static Fragment newInstance(int pos, float scale){
        CarouselItemFragment fragment = new CarouselItemFragment();
        Bundle b = new Bundle();
        b.putInt(POSITION, pos);
        b.putFloat(SCALE, scale);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        if (container == null){
            return null;
        }
        return inflater.inflate(R.layout.main_carousel_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        final int position;


        if (this.getArguments() != null) {
            position = this.getArguments().getInt(POSITION);
            Log.e("carouselPosition", String.valueOf(position));
            float scale = this.getArguments().getFloat(SCALE);
            final TextView itemName = view.findViewById(R.id.pager_textview);
            CarouselLinearLayout root = view.findViewById(R.id.root_container);
            ImageView imageView = view.findViewById(R.id.imgLogo);
            imageView.setImageResource(ListConfig.categoryIconsList[position]);
            itemName.setText(ListConfig.categoryNameList[position]);
            root.setScaleBoth(scale);

            //Set OnClickListener on the Fragment
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getContext(), MenuActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getContext(), BeveragesActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getContext(), FavouritesActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(getContext(), CustomActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(getContext(), RecipeActivity.class));
                            break;
                        case 5:
                            startActivity(new Intent(getContext(), SettingsActivity.class));
                            break;
                    }
                }
            });
        }
    }


}
