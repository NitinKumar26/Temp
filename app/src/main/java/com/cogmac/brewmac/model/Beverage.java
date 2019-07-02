package com.cogmac.brewmac.model;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Map;

public class Beverage {
    private String mPrefsKey, mName, mDescription, mImagePath, mType, mSubType;
    private static ArrayList<Beverage> mBeverages = new ArrayList<>();
    private static int mBevCounter;
    private static final String mBevPrefsFile = "brewmac_beverages";
    private static final String mCounterKey = "beverage_count";

    //Constructor 1
    public Beverage(String name, String description, String imagePath, String type, String subType){
        this.mName = name;
        this.mDescription = description;
        this.mImagePath = imagePath;
        this.mType = type;
        this.mSubType = subType;
    }

    //Constructor 2
    public Beverage(String prefId, @NonNull String str){
        String[] strs = str.split(";;;");
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
        boolean flag = true;
        key = "bev;" + mBevCounter;
        while (flag){
            flag = false;
            value = prefs.getString(key , "");
            if (value.length() > 1)
                flag = true;
            else
                break;
        }
        beverage.setmPrefsKey(key);
        value = beverage.getmName() + ";;;" + beverage.getmDescription() + ";;;" + beverage.getmImagePath() + ";;;" + beverage.getmType() +
                ";;;" + beverage.getmSubType() + ";;;";
        editor.putString(key, value);
        key = mCounterKey;
        editor.putInt(key, mBevCounter);
        editor.apply();
        return false;
    }


    public static boolean removeBaseBeverage(Context cxt, Beverage bb){
        int len = mBeverages.size();
        int index = -1;
        for(int i=0; i<len; i++){
            Beverage tbb = mBeverages.get(i);
            if(tbb.getmPrefsKey().matches(bb.getmPrefsKey())){
                index = i;
                break;
            }
        }
        if(index < 0) return false;
        mBeverages.remove(index);
        SharedPreferences prefs = cxt.getSharedPreferences(mBevPrefsFile, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(bb.getmPrefsKey());
        editor.apply();
        return true;
    }

    public static boolean updateBaseBeverage(Context cxt, Beverage bb){
        int len = mBeverages.size();
        int index = -1;
        for(int i=0; i<len; i++){
            Beverage tbb = mBeverages.get(i);
            if(tbb.getmPrefsKey().matches(bb.getmPrefsKey())){
                index = i;
                break;
            }
        }

        if(index < 0) return false;
        Beverage tbb = mBeverages.get(index);
        tbb.setmName(bb.getmName());
        tbb.setmType(bb.getmType());
        tbb.setmSubType(bb.getmSubType());
        tbb.setmImagePath(bb.getmImagePath());
        tbb.setmDescription(bb.getmDescription());
        bb = tbb;
        String key = bb.getmPrefsKey();
        String value;

        SharedPreferences prefs = cxt.getSharedPreferences(mBevPrefsFile, 0);
        SharedPreferences.Editor editor = prefs.edit();

        value = prefs.getString(key, "");
        if (value.length() < 2)
            return false;
        value = bb.getmName() + ";;;" + bb.getmDescription() + ";;;" + bb.getmImagePath() + ";;;" + bb.getmType() + ";;;" + bb.getmSubType() + ";;;";
        editor.putString(key, value);
        editor.apply();
        return true;
    }


    public void setmPrefsKey(String mPrefsKey) {
        this.mPrefsKey = mPrefsKey;
    }

    public static boolean readAllBeverages(@NonNull Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mBevPrefsFile, 0);
        String key = mCounterKey;
        int counter = sharedPreferences.getInt(key, 0);
        mBevCounter = counter;
        String value;
        mBeverages.clear();
        for(int i = 1;  i <= counter; i++) {
            key = "bev;" + i;
            value = sharedPreferences.getString(key, "");
            if (value.length() > 1) {
                try {
                    Beverage beverage = new Beverage(key, value);
                    mBeverages.add(beverage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Nullable
    public static Beverage getObjectByName(String name){
        int len = mBeverages.size();
        name = name.trim().toLowerCase();
        for(int i=0; i < len; i++){
            Beverage tbb = mBeverages.get(i);
            if(tbb.getmName().trim().toLowerCase().matches(name)){
                return tbb;
            }
        }
        return null;
    }

    @Nullable
    public static Beverage getObjectByKey(String id){
        int len = mBeverages.size();
        for(int i=0; i < len; i++){
            Beverage tbb = mBeverages.get(i);
            if(tbb.getmPrefsKey().matches(id)){
                return tbb;
            }
        }
        return null;
    }

    public static boolean removeAllBaseBeverages(@NonNull Context cxt){
        SharedPreferences prefs = cxt.getSharedPreferences(mBevPrefsFile, 0);
        SharedPreferences.Editor editor = prefs.edit();
        int counter = prefs.getInt(mCounterKey, 0);
        mBeverages.clear();
        String key;
        String value;
        for(int i=1; i<=counter; i++){
            key = "bev;"+i;
            editor.remove(key);
        }
        editor.apply();
        mBevCounter = 0;
        editor.putInt(mCounterKey, mBevCounter);
        editor.commit();
        System.out.println("===============> " + mBevPrefsFile );
        SharedPreferences pref = cxt.getSharedPreferences(mBevPrefsFile, Context.MODE_PRIVATE);
        Map<String, ?> map = pref.getAll();
        System.out.println("++++++++++ Content  => entries_count : " + map.size());
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            System.out.println("++++++++++ Content  => " + entry.getKey() + " : " + entry.getValue());
        }
        for (String key2 : map.keySet()) {
            System.out.println("++++++++++ Content2  => " + key2 + " : " + map.get(key2));
        }
        return true;
    }

    public String getmPrefsKey() {
        return mPrefsKey;
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


    public String getmBevPrefsFile() {
        return mBevPrefsFile;
    }

    public int getmBevCounter() {
        return mBevCounter;
    }

}
