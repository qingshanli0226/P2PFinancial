package com.example.month6.view;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.month6.view.mainactivity_frag.Frag1;
import com.example.month6.view.mainactivity_frag.Frag2;
import com.example.month6.view.mainactivity_frag.Frag3;
import com.example.month6.view.mainactivity_frag.Frag4;
import com.example.month6.R;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private RadioGroup radioGroup;
    ArrayList<Fragment> list=new ArrayList<>();

    Frag1 f1=new Frag1();
    Frag2 f2=new Frag2();
    Frag3 f3=new Frag3();
    Frag4 f4=new Frag4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        showFragment(f1);
    }

    private void initView() {
        radioGroup = findViewById(R.id.radioGroup);
        //底部监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioBut1: showFragment(f1);break;
                    case R.id.radioBut2: showFragment(f2);break;
                    case R.id.radioBut3: showFragment(f3);break;
                    case R.id.radioBut4: showFragment(f4);break;
                }
            }
        });
    }

    //加载fragment
    private void showFragment(Fragment frag){
        if (!frag.isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,frag).commit();
            list.add(frag);
        }
        for (Fragment i : list){
            getSupportFragmentManager().beginTransaction().hide(i).commit();
        }
        getSupportFragmentManager().beginTransaction().show(frag).commit();
    }
}
