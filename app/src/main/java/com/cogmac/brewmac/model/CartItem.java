package com.cogmac.brewmac.model;

public class CartItem extends DrinkItem {
    private String mDrinkName;
    private String mDrinkQuantity;

    public CartItem(String mDrinkName) {
        this.mDrinkName = mDrinkName;
    }

    public CartItem(String drinkName, String drinkQuantity){
        this.mDrinkName = drinkName;
        this.mDrinkQuantity = drinkQuantity;
    }

    public String getDrinkName() {
        return mDrinkName;
    }

    public void setDrinkName(String mDrinkName) {
        this.mDrinkName = mDrinkName;
    }

    public String getDrinkQuantity() {
        return mDrinkQuantity;
    }

    public void setDrinkQuantity(String mDrinkQuantity) {
        this.mDrinkQuantity = mDrinkQuantity;
    }
}
