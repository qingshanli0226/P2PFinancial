package jni.example.lib_core.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import jni.example.lib_core.mvp.presenter.IPresenter;
import jni.example.lib_core.mvp.view.IView;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, IView {

    public P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        setWindow();
        init();
        initData();

    }

    @Override
    public void showToast(String msg) {
        if(TextUtils.isEmpty(msg)){
            return;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        //当界面销毁时 ，调用主持人销毁方法
        if(presenter!=null)
            presenter.destroy();
        super.onDestroy();
    }
}
