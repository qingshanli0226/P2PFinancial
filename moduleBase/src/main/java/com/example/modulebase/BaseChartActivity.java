package com.example.modulebase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import com.gyf.immersionbar.ImmersionBar;

abstract public class BaseChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ImmersionBar.with(this).init();
        initView();
        initTitle();
        initChart();
        setChartData();
    }

    protected abstract void initView();

    protected abstract void setChartData();

    protected abstract void initChart();

    protected abstract void initTitle();

    protected abstract int getLayoutId();
}
