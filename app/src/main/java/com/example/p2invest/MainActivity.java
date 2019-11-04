package com.example.p2invest;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup gr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_ff,new HomeFragment()).commit();
        initView();
         initChange();
    }

    private void initChange() {
        gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.rb1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_ff,new HomeFragment()).commit();
                }
                else    if (i==R.id.rb2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_ff,new TouziFragment()).commit();
                }
                else    if (i==R.id.rb3){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_ff,new WodeFragment()).commit();
                }
                else    if (i==R.id.rb4){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_ff,new MoreFragment()).commit();
                }
            }
        });
    }

    private void initView() {
        gr = (RadioGroup) findViewById(R.id.gr);

    }
}
