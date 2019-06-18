package com.cogmac.brewmac.model;

public class DrinkItem {
    private  String mDrinkTitle;
    private  int mDrinkIcon;

    public DrinkItem(String mDrinkTitle, int mDrinkIcon) {
        this.mDrinkTitle = mDrinkTitle;
        this.mDrinkIcon = mDrinkIcon;
    }

    public String getmDrinkTitle() {
        return mDrinkTitle;
    }

    public int getmDrinkIcon() {
        return mDrinkIcon;
    }

    public void setmDrinkIcon(int mDrinkIcon) {
        this.mDrinkIcon = mDrinkIcon;
    }

    public void setmDrinkTitle(String mDrinkTitle) {
        this.mDrinkTitle = mDrinkTitle;
    }
}
