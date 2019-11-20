package com.example.common.diyviews.utils;

import android.widget.EditText;
import android.widget.TextView;

public class TextEmpty {
    public static boolean isTextEmpty(TextView...textViews){
        for (int i=0;i<textViews.length;i++){
            if (textViews[i].getText().toString().isEmpty()){
              return true;
            }
        }
        return false;
    }
    public static boolean isEditEmpty(EditText...editTexts){
        for (int i=0;i<editTexts.length;i++){
            if (editTexts[i].getText().toString().isEmpty()){
                return true;
            }
        }
        return false;
    }
}
