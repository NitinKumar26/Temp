package com.cogmac.brewmac.model;

public class DrinkItem {
    private  String mDrinkTitle;
    private  int mDrinkIcon;

    public DrinkItem(String mDrinkTitle, int mDrinkIcon) {
        this.mDrinkTitle = mDrinkTitle;
        this.mDrinkIcon = mDrinkIcon;
    }

    public DrinkItem(String mDrinkTitle){
        this.mDrinkTitle = mDrinkTitle;
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


    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        // if both the object references are
        // referring to the same object.
        if(this == obj)
            return true;

        // it checks if the argument is of the
        // type Geek by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof Geek)) return false; ---> avoid.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        DrinkItem drinkItem = (DrinkItem) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (drinkItem.mDrinkTitle.equals(this.mDrinkTitle));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
