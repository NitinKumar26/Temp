package com.cogmac.brewmac.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Beverage {
    private String mPrefsKey, mName, mDescription, mImagePath, mType, mSubType, mCounterKey, mBevPrefsFile;
    private ArrayList<Beverage> mBeverages;
    private int mBevCounter;

    //Constructor 1
    public Beverage(String name, String description, String imagePath, String type, String subType){
        this.mName = name;
        this.mDescription = description;
        this.mImagePath = imagePath;
        this.mType = type;
        this.mSubType = subType;
    }

    //Constructor 2
    public Beverage(String prefId, String str){
        String[] strs = str.split(";;;");
        this.mPrefsKey = prefId;
        this.mName = strs[0];
        this.mDescription = strs[1];
        this.mImagePath = strs[2];
        this.mType = strs[3];
        this.mSubType = strs[4];
    }

    public boolean addBeverage(Context context, Beverage beverage){
        int length = mBeverages.size();
        int index = -1;
        String name = beverage.mName.trim().toLowerCase();
        for (int i=0; i < length; i++){
            Beverage listBeverage = mBeverages.get(i);
            if (listBeverage.mName.toLowerCase().trim().equals(name)){
                index = i;
                break;
            }
        }
        if (index >= 0)
            return false;
        mBeverages.add(beverage);
        String key, value;
        SharedPreferences prefs = context.getSharedPreferences(mBevPrefsFile, 0);
        SharedPreferences.Editor editor = prefs.edit();

        return false;
    }

    public String getmPrefsKey() {
        return mPrefsKey;
    }

    public void setmPrefsKey(String mPrefsKey) {
        this.mPrefsKey = mPrefsKey;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmSubType() {
        return mSubType;
    }

    public void setmSubType(String mSubType) {
        this.mSubType = mSubType;
    }

    public String getmCounterKey() {
        return mCounterKey;
    }

    public void setmCounterKey(String mCounterKey) {
        this.mCounterKey = mCounterKey;
    }

    public String getmBevPrefsFile() {
        return mBevPrefsFile;
    }

    public void setmBevPrefsFile(String mBevPrefsFile) {
        this.mBevPrefsFile = mBevPrefsFile;
    }

    public ArrayList<Beverage> getmBeverages() {
        return mBeverages;
    }

    public void setmBeverages(ArrayList<Beverage> mBeverages) {
        this.mBeverages = mBeverages;
    }

    public int getmBevCounter() {
        return mBevCounter;
    }

    public void setmBevCounter(int mBevCounter) {
        this.mBevCounter = mBevCounter;
    }
}
