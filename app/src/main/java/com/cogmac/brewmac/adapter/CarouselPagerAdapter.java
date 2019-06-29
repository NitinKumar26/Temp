package com.cogmac.brewmac.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.activity.MainActivity;
import com.cogmac.brewmac.fragment.CarouselItemFragment;
import com.cogmac.brewmac.utils.CarouselLinearLayout;
import com.cogmac.brewmac.utils.ListConfig;
import java.util.Objects;

public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    private final static float BIG_SCALE = 1.0f;
    private final static float SMALL_SCALE = 0.7f;
    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private final FragmentManager fragmentManager;
    private float scale;

    public CarouselPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.fragmentManager = fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == MainActivity.FIRST_PAGE)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CarouselItemFragment.newInstance(position, scale);

    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = ListConfig.categoryIconsList.length;
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

    private CarouselLinearLayout getRootView(int position) {
        return (CarouselLinearLayout) Objects.requireNonNull(Objects.requireNonNull(fragmentManager.findFragmentByTag(this.getFragmentTag(position)))
                .getView()).findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + MainActivity.main_viewpager.getId() + ":" + position;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
