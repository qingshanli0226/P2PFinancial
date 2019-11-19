package com.example.administrator.p2pdemotext.ui.SecondaryPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.R;

public class AboutUsActivity extends AppCompatActivity {
    private Button activityButton;
    private TextView homeActivityTittleBarId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        activityButton = (Button) findViewById(R.id.activityButton);
        homeActivityTittleBarId = (TextView) findViewById(R.id.homeActivityTittleBarId);
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
