package com.example.modulecommon.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MarqueTextView extends TextView {
    public MarqueTextView(Context context) {
        super(context);
    }

    public MarqueTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
