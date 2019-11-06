package com.example.month6.view.mainactivity_frag;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.month6.R;

import java.lang.reflect.Type;

public class MoreFrag extends BaseFragment {

    public MoreFrag(Context fragmentContext) {
        super(fragmentContext);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.more_frag;
    }

    @Override
    protected Type getFragDataClass() {
        return null;
    }

    @Override
    public void setDataSuccess(Object object) {

    }
}
