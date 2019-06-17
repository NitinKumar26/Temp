package com.cogmac.brewmac.adapter;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.activity.MainActivity;
import com.cogmac.brewmac.fragment.CarouselItemFragment;
import com.cogmac.brewmac.utils.CarouselLinearLayout;
import com.cogmac.brewmac.utils.ListConfig;

public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private final static float BIG_SCALE = 1.0f;
    private final static float SMALL_SCALE = 0.6f;
    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private final MainActivity context;
    private final FragmentManager fragmentManager;
    private float scale;

    public CarouselPagerAdapter(MainActivity context, FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == MainActivity.FIRST_PAGE)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % ListConfig.categoryIconsList.length;

            Log.e("position", String.valueOf(position));
            //Log.e("listLength", String.valueOf(ListConfig.categoryIconsList.length));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return CarouselItemFragment.newInstance(context, position, scale);

    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = ListConfig.categoryIconsList.length * MainActivity.LOOPS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                CarouselLinearLayout cur = getRootView(position);
                CarouselLinearLayout next = getRootView(position + 1);
                cur.setScaleBoth(BIG_SCALE - (DIFF_SCALE * positionOffset));
                next.setScaleBoth(SMALL_SCALE + (DIFF_SCALE * positionOffset));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {



    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressWarnings("ConstantConditions")
    private CarouselLinearLayout getRootView(int position) {
        return (CarouselLinearLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + MainActivity.main_viewpager.getId() + ":" + position;
    }
}
