package com.cogmac.brewmac.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cogmac.brewmac.R;
import com.cogmac.brewmac.activity.MenuActivity;
import com.cogmac.brewmac.fragment.VerticalCarouselItemFragment;
import com.cogmac.brewmac.utils.ListConfig;
import com.cogmac.brewmac.utils.VerticalCarouselLinearLayout;

public class TempAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private final static float BIG_SCALE = 1.0f;
    private final static float SMALL_SCALE = 0.6f;
    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private final MenuActivity context;
    private final FragmentManager fragmentManager;
    private float scale;

    public TempAdapter(MenuActivity context, FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == MenuActivity.FIRST_PAGE)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % ListConfig.categoryIconsList.length;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return VerticalCarouselItemFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = MenuActivity.COUNT * MenuActivity.LOOPS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                VerticalCarouselLinearLayout cur = getRootView(position);
                VerticalCarouselLinearLayout next = getRootView(position + 1);
                cur.setScaleBothVertical(BIG_SCALE - (DIFF_SCALE * positionOffset));
                next.setScaleBothVertical(SMALL_SCALE + (DIFF_SCALE * positionOffset));
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
    private VerticalCarouselLinearLayout getRootView(int position) {
        return (VerticalCarouselLinearLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + context.verticalViewpager.getId() + ":" + position;
    }
}
