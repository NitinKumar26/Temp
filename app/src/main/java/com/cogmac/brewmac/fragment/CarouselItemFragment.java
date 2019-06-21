package com.cogmac.brewmac.fragment;

import android.content.Context;
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
import com.cogmac.brewmac.activity.MenuActivity;
import com.cogmac.brewmac.activity.RecipeActivity;
import com.cogmac.brewmac.activity.SettingsActivity;
import com.cogmac.brewmac.utils.CarouselLinearLayout;
import com.cogmac.brewmac.utils.ListConfig;

public class CarouselItemFragment extends Fragment {
    private static final String POSITION = "position";
    private static final String SCALE = "scale";

    public static Fragment newInstance(Context context, int pos, float scale){
        Bundle b = new Bundle();
        b.putInt(POSITION, pos);
        b.putFloat(SCALE, scale);
        return Fragment.instantiate(context, CarouselItemFragment.class.getName(),  b);
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
        super.onViewCreated(view, savedInstanceState);
        final int position;

        if (this.getArguments() != null) {
            position = this.getArguments().getInt(POSITION);
            float scale = this.getArguments().getFloat(SCALE);
            Log.e("positionNew", String.valueOf(position));
            TextView itemName = view.findViewById(R.id.pager_textview);
            CarouselLinearLayout root = view.findViewById(R.id.root_container);
            //private static final String DRAWABLE_RESOURCE = "resource";
            ImageView imageView = view.findViewById(R.id.imgLogo);
            imageView.setImageResource(ListConfig.categoryIconsList[position]);
            itemName.setText(ListConfig.categoryNameList[position]);
            root.setScaleBoth(scale);
            //Set OnClickListener on the Fragment
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position){
                        case 1:
                            startActivity(new Intent(getContext(), MenuActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getContext(), CustomActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(getContext(), BeveragesActivity.class));
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
