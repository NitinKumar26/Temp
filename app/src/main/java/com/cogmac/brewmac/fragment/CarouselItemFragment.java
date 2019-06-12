package com.cogmac.brewmac.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cogmac.brewmac.MenuActivity;
import com.cogmac.brewmac.R;
import com.cogmac.brewmac.SettingsActivity;
import com.cogmac.brewmac.utils.CarouselLinearLayout;
import com.cogmac.brewmac.utils.ListConfig;

public class CarouselItemFragment extends Fragment {

    private static final String POSITION = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURCE = "resource";
    public ImageView imageView;

    public static Fragment newInstance(Context context, int pos, float scale){
        Bundle b = new Bundle();
        b.putInt(POSITION, pos);
        b.putFloat(SCALE, scale);
        return Fragment.instantiate(context, CarouselItemFragment.class.getName(),  b);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int position;
        if (this.getArguments() != null) {
            position = this.getArguments().getInt(POSITION);
            float scale = this.getArguments().getFloat(SCALE);
            TextView itemName = view.findViewById(R.id.pager_textview);
            CarouselLinearLayout root = view.findViewById(R.id.root_container);
            imageView = view.findViewById(R.id.imgLogo);
            imageView.setImageResource(ListConfig.categoryIconsList[position]);
            itemName.setText(ListConfig.categoryNameList[position]);
            root.setScaleBoth(scale);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MenuActivity.class));

            }
        });

    }
}
