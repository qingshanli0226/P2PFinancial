package com.bw.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.bw.common.UIUtils;

public class SpUtils {

    private static SpUtils spUtils;

    public SpUtils() {
    }

    private SharedPreferences sp = UIUtils.getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);

    public static SpUtils getInstance() {

        if (spUtils == null){
            spUtils = new SpUtils();
        }

        return spUtils;
    }

    @SuppressLint("ApplySharedPref")
    public void cave(String key, Object values){
        if (values instanceof String){
            sp.edit().putString(key, (String) values).commit();
        }else if (values instanceof Boolean){
            sp.edit().putBoolean(key, (Boolean) values).commit();
        }else if (values instanceof Integer){
            sp.edit().putInt(key, (Integer) values).commit();
        }
    }

    public String getString(String key){
        String string = sp.getString(key, "");
        return string;
    }

    public Boolean getBoolean(String key){
        boolean aBoolean = sp.getBoolean(key, true);
        return aBoolean;
    }

    public int getInteger(String key){
        int anInt = sp.getInt(key, 0);
        return anInt;
    }

}
