package com.example.modulebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.modulecommon.Constructor;

import butterknife.ButterKnife;
//Fragment基类
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(),container,false);
        ButterKnife.bind(this,view);
        initData();
        loadData();
        initTitle();
       return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    protected abstract void initTitle();

    protected abstract void initData();

    protected abstract void loadData();

    //绑定布局
    protected abstract int setLayout();
    //启动新的Activity
    public void goToActivity(Class activity, Bundle bundle){
        Intent intent = new Intent(getContext(), activity);
        if (bundle != null && bundle.size() != 0)
            intent.putExtra(Constructor.BUNDLE,bundle);

        startActivity(intent);
    }


}
