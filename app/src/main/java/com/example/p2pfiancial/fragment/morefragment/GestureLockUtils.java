package com.example.p2pfiancial.fragment.morefragment;

public class GestureLockUtils {
    private static GestureLockUtils instance;

    public static GestureLockUtils getInstance(){
        if (instance == null){
            instance = new GestureLockUtils();
        }
        return instance;
    }




}
