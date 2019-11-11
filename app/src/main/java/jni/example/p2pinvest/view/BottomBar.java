package jni.example.p2pinvest.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import jni.example.p2pinvest.R;

public class BottomBar extends LinearLayout {
    private ImageView homeImage;
    private TextView homeTextView;
    private ImageView investImage;
    private TextView investTextView;
    private ImageView assetsImage;
    private TextView assetsTextView;
    private ImageView moreImage;
    private TextView moreTextView;
    private LinearLayout home;
    private LinearLayout invest;
    private LinearLayout assets;
    private LinearLayout more;

    private TabOnClickListener listener;

    public BottomBar(Context context) {
        super(context);
        init(context);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public interface TabOnClickListener{
        void getIndex(int index);
    }

    public void setListener(TabOnClickListener listener) {
        this.listener = listener;
    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.bottom_bar,this);
        homeImage = (ImageView) findViewById(R.id.home_image);
        homeTextView = (TextView) findViewById(R.id.home_textView);
        investImage = (ImageView) findViewById(R.id.invest_image);
        investTextView = (TextView) findViewById(R.id.invest_textView);
        assetsImage = (ImageView) findViewById(R.id.assets_image);
        assetsTextView = (TextView) findViewById(R.id.assets_textView);
        moreImage = (ImageView) findViewById(R.id.more_image);
        moreTextView = (TextView) findViewById(R.id.more_textView);

        home = (LinearLayout) findViewById(R.id.home);
        invest = (LinearLayout) findViewById(R.id.invest);
        assets = (LinearLayout) findViewById(R.id.assets);
        more = (LinearLayout) findViewById(R.id.more);

        home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getIndex(0);
                setAll();
                setHomeImage(R.mipmap.bottom02);
                setHomeTextColor("#1E88E5");
            }
        });
        invest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getIndex(1);
                setAll();
                setInvestImage(R.mipmap.bottom04);
                setInvestTextView("#1E88E5");
            }
        });
        assets.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getIndex(2);
                setAll();
                setAssetsImage(R.mipmap.bottom06);
                setAssetsTextView("#D81B47");
            }
        });
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getIndex(3);
                setAll();
                setMoreImage(R.mipmap.bottom08);
                setMoreTextView("#1E88E5");
            }
        });
    }

    public void setAll(){
        setHomeImage(R.mipmap.bottom01);
        setHomeTextColor("#777575");
        setInvestImage(R.mipmap.bottom03);
        setInvestTextView("#777575");
        setAssetsImage(R.mipmap.bottom05);
        setAssetsTextView("#777575");
        setMoreImage(R.mipmap.bottom07);
        setMoreTextView("#777575");
    }

    public void setHomeImage(int image_url) {
        this.homeImage.setImageResource(image_url);
    }

    public void setHomeTextColor(String color) {
        this.homeTextView.setTextColor(Color.parseColor(color));
    }

    public void setInvestImage(int image_url) {
        this.investImage.setImageResource(image_url);
    }

    public void setInvestTextView(String color) {
        this.investTextView.setTextColor(Color.parseColor(color));
    }


    public void setAssetsImage(int image_url) {
        this.assetsImage.setImageResource(image_url);
    }

    public void setAssetsTextView(String color) {
        this.assetsTextView.setTextColor(Color.parseColor(color));
    }

    public void setMoreImage(int image_url) {
        this.moreImage.setImageResource(image_url);
    }

    public void setMoreTextView(String color) {
        this.moreTextView.setTextColor(Color.parseColor(color));
    }


}
